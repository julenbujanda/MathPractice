package ga.julen.mathpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private int correctResult;
    private TextView lblQuestion;
    private EditText txtResult;
    private TextView lblRemainingCalcs;
    private TextView lblScore;
    private TextView lblIsCorrect;

    private Button btnCalculate;

    private int level;

    private int remainingCalculations;
    private int score;

    private int generarNumero() {
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

    private void cerrarTeclado() {
        ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblIsCorrect = findViewById(R.id.lbl_isCorrect);
        txtResult = findViewById(R.id.txt_result);
        btnCalculate = findViewById(R.id.btn_calculate);
        lblQuestion = findViewById(R.id.lbl_question);
        Spinner levelSpinner = findViewById(R.id.levels_spinner);
        ArrayAdapter<CharSequence> levelsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.levels_array, android.R.layout.simple_spinner_item);
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelsAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        level = 1;
                        nuevaOperacion();
                        break;
                    case 1:
                        level = 2;
                        nuevaOperacion();
                        break;
                    case 2:
                        level = 3;
                        nuevaOperacion();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final EditText txtCalculations = findViewById(R.id.txt_calculations);
        final Button btnSetCalcs = findViewById(R.id.btn_set_calcs);
        lblScore = findViewById(R.id.lbl_score);
        lblRemainingCalcs = findViewById(R.id.lblRemainingNumber);
        btnSetCalcs.setEnabled(false);
        txtCalculations.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (txtCalculations.getText().toString().equals("")) {
                    btnSetCalcs.setEnabled(false);
                } else {
                    btnSetCalcs.setEnabled(true);
                }
            }
        });
        btnSetCalcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remainingCalculations = Integer.parseInt(txtCalculations.getText().toString());
                txtCalculations.setText("");
                lblRemainingCalcs.setText(Integer.toString(remainingCalculations));
            }
        });

        remainingCalculations = 5;
        score = 0;
        lblRemainingCalcs.setText(Integer.toString(remainingCalculations));
        level = 1;
        nuevaOperacion();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarTeclado();
                int resultadoUsuario = Integer.parseInt(txtResult.getText().toString());
                if (resultadoUsuario == correctResult) {
                    lblIsCorrect.setText(R.string.correct);
                    remainingCalculations--;
                    score++;
                    updateData();
                    FancyToast.makeText(getApplicationContext(), "Correcto!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    nuevaOperacion();
                } else {
                    lblIsCorrect.setText(R.string.incorrect);
                }
                if (remainingCalculations == 0) {
                    endGame();
                }
            }
        });
    }

    private void endGame() {
        lblIsCorrect.setText(R.string.correct);
        btnCalculate.setEnabled(false);
    }

    private void updateData() {
        lblRemainingCalcs.setText(Integer.toString(remainingCalculations));
        lblScore.setText(Integer.toString(score));
    }

}
