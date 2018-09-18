package ga.julen.mathpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private int correctResult;
    private TextView lblQuestion;
    private EditText txtResult;
    private static int level;

    private static int generarNumero() {
        switch (level) {
            case 1:
                return (int) (10 * Math.random());
            case 2:
                return (int) (100 * Math.random());
            case 3:
                return (int) (100 * Math.random());
            default:
                return 0;
        }
    }

    private void nuevaOperacion() {
        txtResult.setText("");
        int number1 = generarNumero();
        int number2;
        if (level != 3) {
            number2 = generarNumero();
            correctResult = number1 + number2;
            lblQuestion.setText(number1 + " + " + number2 + " =");
        } else {
            do {
                number2 = generarNumero();
            } while (number2 >= number1);
            correctResult = number1 - number2;
            lblQuestion.setText(number1 + " - " + number2 + " =");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView lblIsCorrect = findViewById(R.id.lbl_isCorrect);
        txtResult = findViewById(R.id.txt_result);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        Button btnLevel1 = findViewById(R.id.btn_level_1);
        Button btnLevel2 = findViewById(R.id.btn_level_2);
        Button btnLevel3 = findViewById(R.id.btn_level_3);
        lblQuestion = findViewById(R.id.lbl_question);

        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 1;
                nuevaOperacion();
            }
        });
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 2;
                nuevaOperacion();
            }
        });
        btnLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                level = 3;
                nuevaOperacion();
            }
        });

        level = 1;
        nuevaOperacion();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                int resultadoUsuario = Integer.parseInt(txtResult.getText().toString());
                if (resultadoUsuario == correctResult) {
                    lblIsCorrect.setText(R.string.correct);
                    FancyToast.makeText(getApplicationContext(), "Correcto!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    nuevaOperacion();
                } else {
                    lblIsCorrect.setText(R.string.incorrect);
                }
            }
        });
    }
}
