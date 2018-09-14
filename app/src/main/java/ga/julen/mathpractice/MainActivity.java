package ga.julen.mathpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int number1;
    private int number2;
    private int correctResult;
    private TextView lblQuestion;
    private EditText txtResult;

    private static int generarNumero() {
        return (int) (10 * Math.random());
    }

    private void nuevaOperacion(){
        txtResult.setText("");
        number1 = generarNumero();
        number2 = generarNumero();
        correctResult = number1 + number2;
        lblQuestion.setText(number1 + " + " + number2 + " =");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView lblIsCorrect = findViewById(R.id.lbl_isCorrect);
        txtResult = findViewById(R.id.txt_result);
        Button btnCalculate = findViewById(R.id.btn_calculate);
        lblQuestion = findViewById(R.id.lbl_question);
        nuevaOperacion();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resultadoUsuario = Integer.parseInt(txtResult.getText().toString());
                if (resultadoUsuario == correctResult) {
                    lblIsCorrect.setText(R.string.correct);
                    nuevaOperacion();
                } else {
                    lblIsCorrect.setText(R.string.incorrect);
                }
            }
        });
    }
}
