package com.astreak.stillcalculator

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.astreak.stillcalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLICATION = '*'
    private val DIVISION = '/'
    private val EQU = '='
    private val EXTRA = '@'
    private val MODULUS = '%'
    private var ACTION = 0.toChar()
    private var val1 = Double.NaN
    private var val2 = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        calculate()
    }

    private fun calculate(View v) {

        binding.button1.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "1"
        }
        binding.button2.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "2"
        }

        binding.button3.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "3"
        }

        binding.button4.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "4"
        }

        binding.button5.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "5"
        }

        binding.button6.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "6"
        }

        binding.button7.setOnClickListener {
                ifErrorOnOutput()
                exceedLength()
                binding.input.text = binding.input.text.toString() + "7"
        }

        binding.button8.setOnClickListener {
        ifErrorOnOutput()
        exceedLength()
        binding.input.text = binding.input.text.toString() + "8"
    }

        binding.button9.setOnClickListener {
        ifErrorOnOutput()
        exceedLength()
        binding.input.text = binding.input.text.toString() + "9"
    }

    binding.button0.setOnClickListener {
        ifErrorOnOutput()
        exceedLength()
        binding.input.text = binding.input.text.toString() + "0"
    }

    binding.buttonDot.setOnClickListener {
        exceedLength()
        binding.input.text = binding.input.text.toString() + "."
    }

    binding.buttonPara1.setOnClickListener {
        if (binding.input.text.isNotEmpty()) {
            ACTION = MODULUS
            operation()
            if (!ifReallyDecimal()) {
                binding.output.text = "$val1%"
            } else {
                binding.output.text = "${val1.toInt()}%"
            }
            binding.input.text = null
        } else {
            binding.output.text = "Error"
        }
    }

    binding.buttonAdd.setOnClickListener {
        if (binding.input.text.isNotEmpty()) {
            ACTION = ADDITION
            operation()
            if (!ifReallyDecimal()) {
                binding.output.text = "$val1+"
            } else {
                binding.output.text = "${val1.toInt()}+"
            }
            binding.input.text = null
        } else {
            binding.output.text = "Add"
        }
    }

    binding.buttonSub.setOnClickListener {
        if (binding.input.text.isNotEmpty()) {
            ACTION = SUBTRACTION
            operation()
            if (binding.input.text.isNotEmpty()) {
                if (!ifReallyDecimal()) {
                    binding.output.text = "$val1-"+ "$ACTION"
                } else {
                    binding.output.text = "${val1.toInt()}-"
                }
            }
            binding.input.text = null
        } else {
            binding.output.text = "Sub"
        }
    }

    binding.buttonMulti.setOnClickListener {
        if (binding.input.text.isNotEmpty()) {
            ACTION = MULTIPLICATION
            operation()
            if (!ifReallyDecimal()) {
                binding.output.text = "$val1×"
            } else {
                binding.output.text = "${val1.toInt()}×"
            }
            binding.input.text = null
        } else {
            binding.output.text = "Error"
        }
    }


    binding.buttonDivide.setOnClickListener {
        if (binding.input.text.isNotEmpty()) {
            ACTION = DIVISION
            operation()
            if (ifReallyDecimal()) {
                binding.output.text = "${val1.toInt()}/"
            } else {
                binding.output.text = "$val1/"
            }
            binding.input.text = null
        } else {
            binding.output.text = "Error"
        }
    }
        binding.buttonPara2.setOnClickListener {
            if (binding.output.text.toString().isNotEmpty() || binding.input.text.toString().isNotEmpty()) {
                val1 = binding.input.text.toString().toDouble()
                ACTION = EXTRA
                binding.output.text = "-" + binding.input.getText().toString()
                binding.input.text = ""
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonEqual.setOnClickListener{
            if (binding.input.text.isNotEmpty()) {
                ACTION = EQU
                operation()
                if (!ifReallyDecimal()) {
                    binding.output.text = val1.toString()
                } else {
                    binding.output.text = val1.toInt().toString()
                }
                binding.input.text = null
            } else {
                binding.output.text = "Error"
            }
        }

        binding.buttonClear.setOnClickListener {

                if (binding.input.text.isNotEmpty()) {
                    val name: CharSequence = binding.input.text.toString()
                    binding.input.text = name.subSequence(0, name.length - 1)
                } else {
                    val1 = Double.NaN
                    val2 = Double.NaN
                    binding.input.text = ""
                    binding.output.text = ""
                }
        }
        binding.buttonClear.setOnLongClickListener{
            val1 = Double.NaN
            val2 = Double.NaN
            binding.input.text = ""
            binding.output.text = ""
            true
        }
    }

    private fun operation() {
        if (!val1.isNaN()) {
            if (ACTION == EXTRA) {
                val1 = -val1
            } else {
                val2 = binding.input.text.toString().toDouble()
                when (ACTION) {
                    ADDITION -> val1 += val2
                    SUBTRACTION -> val1 -= val2
                    MULTIPLICATION -> val1 *= val2
                    DIVISION -> val1 /= val2
                    EXTRA -> val1 = -val1
                    MODULUS -> val1 = val1 * 0.01
                    EQU -> {
// Do nothing if equals is pressed multiple times
                    }
                }
            }
        } else {
            val1 = binding.input.text.toString().toDouble()
        }
    }
    private fun ifErrorOnOutput() {
        if (binding.output.text.toString() == "Error") {
            binding.output.text = "";
        }
    }
    private fun noOperation() {
        var inputExpression: String = binding.output.text.toString()
        if (inputExpression.isNotEmpty() && inputExpression != "Error") {
            if (inputExpression.contains("-")) {
                inputExpression = inputExpression.replace("-", "")
                binding.output.text = ""
                val1 = inputExpression.toDouble()
            }
            if (inputExpression.contains("+")) {
                inputExpression = inputExpression.replace("+", "")
                binding.output.text = ""
                val1 = inputExpression.toDouble()
            }
            if (inputExpression.contains("/")) {
                inputExpression = inputExpression.replace("/", "")
                binding.output.text = ""
                val1 = inputExpression.toDouble()
            }
            if (inputExpression.contains("%")) {
                inputExpression = inputExpression.replace("%", "")
                binding.output.text = ""
                val1 = inputExpression.toDouble()
            }
            if (inputExpression.contains("×")) {
                inputExpression = inputExpression.replace("×", "")
                binding.output.text = ""
                val1 = inputExpression.toDouble()
            }
        }
    }
    private fun exceedLength() {
        if (binding.input.text.toString().length > 10) {
            binding.input.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        }
    }
    private fun ifReallyDecimal(): Boolean {
        return val1 == val1.toInt().toDouble()
    }


}

