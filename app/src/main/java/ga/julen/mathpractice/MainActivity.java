package ga.julen.mathpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //Graphical
    private TextView lblQuestion, lblRemainingCalcs, lblScore, lblIsCorrect;
    private EditText txtResult, txtCalculations;
    private Button btnCalculate, btnSetCalcs;

    private int level, remainingCalculations, score;
    private Operacion operation;

    /**
     * Crea una nueva operación
     */
    private void nuevaOperacion() {
        txtResult.setText("");
        operation = new Operacion(level);
        if (level != 3) {
            lblQuestion.setText(operation.getNum1() + " + " + operation.getNum2() + " =");
        } else {
            while (operation.getResult() < 0) {
                operation = new Operacion(level);
            }
            lblQuestion.setText(operation.getNum1() + " - " + operation.getNum2() + " =");
        }

    }

    /**
     * Cierra el teclado de la aplicación
     */
    private void cerrarTeclado() {
        ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexión con la vista
        lblIsCorrect = findViewById(R.id.lbl_isCorrect);
        txtResult = findViewById(R.id.txt_result);
        btnCalculate = findViewById(R.id.btn_calculate);
        lblQuestion = findViewById(R.id.lbl_question);
        Spinner levelSpinner = findViewById(R.id.levels_spinner);
        txtCalculations = findViewById(R.id.txt_calculations);
        btnSetCalcs = findViewById(R.id.btn_set_calcs);
        lblScore = findViewById(R.id.lbl_score);
        lblRemainingCalcs = findViewById(R.id.lblRemainingNumber);

        //Popular levelSpinner
        ArrayAdapter<CharSequence> levelsAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.levels_array, android.R.layout.simple_spinner_item);
        levelsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpinner.setAdapter(levelsAdapter);
        //Acciones del levelSpinner
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos + 1 != level) {
                    score = 0;
                }
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

        //Desactivar o activar botones según el estado
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
        btnCalculate.setEnabled(false);
        txtResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (txtResult.getText().toString().equals("") || remainingCalculations == 0) {
                    btnCalculate.setEnabled(false);
                } else {
                    btnCalculate.setEnabled(true);
                }
            }
        });

        //Aplicar número deoperaciones
        btnSetCalcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remainingCalculations = Integer.parseInt(txtCalculations.getText().toString());
                txtCalculations.setText("");
                lblRemainingCalcs.setText(Integer.toString(remainingCalculations));
                cerrarTeclado();
                if (!txtResult.getText().toString().equals("") && remainingCalculations > 0) {
                    btnCalculate.setEnabled(true);
                }
                score = 0;
            }
        });

        //Calcular resultado
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarTeclado();
                int resultadoUsuario = Integer.parseInt(txtResult.getText().toString());
                if (resultadoUsuario == operation.getResult()) {
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

        //Nivel y número de operaciones por defecto
        level = 1;
        remainingCalculations = 5;
        score = 0;
        updateData();
        nuevaOperacion();

    }

    /**
     * Finaliza el juego actual
     */
    private void endGame() {
        lblIsCorrect.setText(R.string.end);
        btnCalculate.setEnabled(false);
    }

    /**
     * Actualiza los TextView con las variables actuales
     */
    private void updateData() {
        lblRemainingCalcs.setText(Integer.toString(remainingCalculations));
        lblScore.setText(Integer.toString(score));
    }

}
