@file:Suppress("unused")

package ru.ifmo.ctddev.cookmate.service

import org.deeplearning4j.nn.graph.ComputationGraph
import org.deeplearning4j.util.ModelSerializer
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.factory.Nd4j
import org.springframework.stereotype.Service
import ru.ifmo.ctddev.cookmate.model.Recipe
import ru.ifmo.ctddev.cookmate.model.RecipeStep
import java.nio.file.Files
import java.nio.file.Paths
import java.util.regex.Pattern

/**
 * @author  Vadim Semenov (semenov@rain.ifmo.ru)
 */
interface RecognitionService {
    fun recognize(step: RecipeStep, text: String): String
}

@Service("recognitionService")
class RecognitionServiceImpl: RecognitionService {

    val model: ComputationGraph = ModelSerializer.restoreComputationGraph(Paths.get("models").
            resolve("CommandsRecognition.zip").toAbsolutePath().toString())
    val mapping = HashMap<String, Int>()
    val sentenceSize: Int
    val classesCount: Int

    init {
        val result = Files.readAllLines(Paths.get("models", "mapInfo.txt"))
        sentenceSize = result[0].toInt()
        classesCount = result[1].toInt()
        for (r in result.subList(2, result.size)) {
            val data = r.split(",")
            mapping[data[0]] = data[1].toInt()
        }
    }

    override fun recognize(step : RecipeStep, text: String): String {
        val classIdx = getClass(text)
        when (classIdx) {
            0 -> return "Привет" // класс приветсвия
            1 -> return "Next"
            2 -> return "Prev"
            3 -> return "Time"
            4 -> {
                val sb = StringBuilder("Сейчас нужны следующие ингридиенты. ")
                for (p in step.products) {
                    sb.append("Надо ${p.name}, ${p.amount} ")
                }
                return sb.toString()
            }
            5 -> {
                return step.shortDescription
            }
        }
        return ""
    }

    private fun getClass(text : String) : Int{
        model.rnnClearPreviousState()
        val data = getINDArray(text.toLowerCase())

        val lastTimeStep = model.rnnTimeStep(data)[0].tensorAlongDimension(sentenceSize - 1,1,0)

        var curIdx = 0
        var curValue = lastTimeStep.getDouble(0)

        for (i in 0 until classesCount) {
            if (curValue < lastTimeStep.getDouble(i)) {
                curIdx = i
                curValue = lastTimeStep.getDouble(i)
            }
        }
        return curIdx
    }

    private fun getINDArray(text : String) : INDArray {
        val data = IntArray(sentenceSize, {i -> mapping.size})
        val words = text.split(Pattern.compile("\\s"))
        for (i in 0 until sentenceSize) {
            if (i < words.size) {
                if (mapping.containsKey(words[i])) {
                    data[i] = mapping[words[i]]!!;
                }
            }
        }

        val array = Nd4j.zeros(1, mapping.size + 1, sentenceSize)
        for (i in 0 until sentenceSize) {
            array.putScalar(intArrayOf(0, data[i], i), 1.0)
        }
        return array
    }
}