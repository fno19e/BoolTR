package de.feichhorn.booltr.booltr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FrontendInput extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Variablen bestimmen
    Button buttonZero,
            button_one,
            button_delete,
            button_delete_all,
            button_plus,
            button_multiply,
            button_not,
            button_arrow,
            button_darrow,
            button_bracket1,
            button_bracket2,
            button_a,
            button_b,
            button_c,
            button_d,
            button_e,
            button_f,
            button_solve;
    EditText inputExpression;
    String typeMethod = "wt";
    FloatingActionButton lastInputs;
    ArrayList<String> lastInputsArray = new ArrayList();
    Intent lastInputsIntent;

    // Methode, die das Ergebnis eines Aufrufes einer alten Eingabe aus der Historie entgegennimmt und verarbeitet

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                //String mit Namen "input" aus der Activity LastInputs
                inputExpression.setText(data.getStringExtra("input"));
                //Cursor an das Ende der Eingabe setzten
                setCursor();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Variablen initialisieren

        buttonZero = findViewById(R.id.buttonZero);
        button_one = findViewById(R.id.button_one);
        button_delete = findViewById(R.id.button_delete);
        button_plus = findViewById(R.id.button_plus);
        button_multiply = findViewById(R.id.button_multiply);
        button_not = findViewById(R.id.button_not);
        button_arrow = findViewById(R.id.button_arrow);
        button_darrow = findViewById(R.id.button_darrow);
        button_bracket1 = findViewById(R.id.button_bracket1);
        button_bracket2 = findViewById(R.id.button_bracket2);
        button_a = findViewById(R.id.button_a);
        button_b = findViewById(R.id.button_b);
        button_c = findViewById(R.id.button_c);
        button_d = findViewById(R.id.button_d);
        button_e = findViewById(R.id.button_e);
        button_f = findViewById(R.id.button_f);
        button_solve = findViewById(R.id.button_solve);
        lastInputs = findViewById(R.id.lastInputs);
        lastInputsIntent = new Intent(FrontendInput.this, LastInputs.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Titel standartmäßig auf Wertetabelle setzten -> Erste Auswahl im Menü

        setTitle("Wertetabelle");

        //Übergabeparameter, um gewählte Methode erkennen zu können

        typeMethod = "wt";

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_screen = displayMetrics.heightPixels;
        int width_screen = displayMetrics.widthPixels;

        inputExpression = findViewById(R.id.inputExpression);

        //
        //

        //Definition der Eingabefelder des Frontends -> Input-Buttons, Delete-Button, Solve-Button, History-Button

        //
        //

        buttonZero = findViewById(R.id.buttonZero);
        //Maße der Buttons kontrollieren
        buttonZero.setWidth(width_screen / 3);
        buttonZero.setHeight(height_screen / 3);
        //Drücken des Buttons verarbeiten
        View.OnClickListener buttonZeroListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //An die Eingabe ein entsprechendes Zeichen hängen
                inputExpression.setText(inputExpression.getText() + "0");
                //On Press Vibration auslösen
                vibrate();
                //Ausblenden des Buttons für die History, wenn die Eingabe mehr als 15 Zeichen umfasst
                setHistoryInvisible();
                //Cursor an Ende der Eingabe setzen
                setCursor();
            }
        };
        buttonZero.setOnClickListener(buttonZeroListener);

        //
        //

        button_one = findViewById(R.id.button_one);
        button_one.setWidth(width_screen / 3);
        button_one.setHeight(height_screen / 3);
        View.OnClickListener button_one_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "1");
                vibrate();
                setHistoryInvisible();
                setCursor();
            }
        };
        button_one.setOnClickListener(button_one_listener);

        //
        //

        button_delete_all = findViewById(R.id.button_delete);
        final View.OnLongClickListener button_delete_long_listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                inputExpression.setText("");
                setHistoryVisible();
                return true;
            }
        };
        button_delete_all.setOnLongClickListener(button_delete_long_listener);

        //

        button_delete = findViewById(R.id.button_delete);
        button_delete.setWidth(width_screen / 3);
        button_delete.setHeight(height_screen / 3);

        View.OnClickListener button_delete_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input_expression_length = inputExpression.length();
                if (inputExpression.getSelectionStart() != 0) {
                    if (inputExpression.getSelectionStart() == input_expression_length) {
                        if (input_expression_length != 0) {
                            inputExpression.setSelection(input_expression_length);
                            inputExpression.setCursorVisible(true);
                            String input_expression_string = inputExpression.getText().toString();
                            input_expression_string = input_expression_string.substring(0, input_expression_length - 1);
                            inputExpression.setText(input_expression_string);
                            setCursor();
                            vibrate();
                        } else {

                        }
                    } else {
                        int positionCursor = inputExpression.getSelectionStart();
                        int inputExpressionLength = inputExpression.length();
                        String inputExpressionString = inputExpression.getText().toString();
                        inputExpressionString.indexOf(positionCursor);
                        String inputDeleted = inputExpressionString.substring(0, positionCursor - 1) + inputExpressionString.substring(positionCursor, inputExpressionLength);
                        inputExpression.setText(inputDeleted);
                        inputExpression.setSelection(positionCursor-1);
                        vibrate();
                    }
                    setHistoryVisible();
                }
            }
        };
        button_delete.setOnClickListener(button_delete_listener);

        //
        //

        button_plus = findViewById(R.id.button_plus);
        button_plus.setWidth(width_screen / 3);
        button_plus.setHeight(height_screen / 3);
        View.OnClickListener button_plus_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "+");
                vibrate();
                int input_expression_length = inputExpression.length();
                inputExpression.setSelection(input_expression_length);
                if (input_expression_length == 15) {
                    findViewById(R.id.lastInputs).setVisibility(View.INVISIBLE);
                }
                inputExpression.setSelection(input_expression_length);
            }
        };
        button_plus.setOnClickListener(button_plus_listener);

        //
        //

        button_multiply = findViewById(R.id.button_multiply);
        button_multiply.setWidth(width_screen / 3);
        button_multiply.setHeight(height_screen / 3);
        View.OnClickListener button_multiply_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "*");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_multiply.setOnClickListener(button_multiply_listener);

        //
        //

        button_not = findViewById(R.id.button_not);
        button_not.setWidth(width_screen / 3);
        button_not.setHeight(height_screen / 3);
        View.OnClickListener button_not_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "¬");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_not.setOnClickListener(button_not_listener);

        //
        //

        button_arrow = findViewById(R.id.button_arrow);
        button_arrow.setWidth(width_screen / 3);
        button_arrow.setHeight(height_screen / 3);
        View.OnClickListener button_arrow_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "→");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_arrow.setOnClickListener(button_arrow_listener);

        //
        //

        button_darrow = findViewById(R.id.button_darrow);
        button_darrow.setWidth(width_screen / 3);
        button_darrow.setHeight(height_screen / 3);
        View.OnClickListener button_darrow_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "↔");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_darrow.setOnClickListener(button_darrow_listener);

        //
        //

        button_bracket1 = findViewById(R.id.button_bracket1);
        button_bracket1.setWidth(width_screen / 3);
        button_bracket1.setHeight(height_screen / 3);
        View.OnClickListener button_bracket1_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "(");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_bracket1.setOnClickListener(button_bracket1_listener);

        //
        //

        button_bracket2 = findViewById(R.id.button_bracket2);
        button_bracket2.setWidth(width_screen / 3);
        button_bracket2.setHeight(height_screen / 3);
        View.OnClickListener button_bracket2_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + ")");
                vibrate();
                int input_expression_length = inputExpression.length();
                inputExpression.setSelection(input_expression_length);
                if (input_expression_length == 15) {
                    findViewById(R.id.lastInputs).setVisibility(View.INVISIBLE);
                }
                inputExpression.setSelection(input_expression_length);
            }
        };
        button_bracket2.setOnClickListener(button_bracket2_listener);

        //
        //

        button_a = findViewById(R.id.button_a);
        button_a.setWidth(width_screen / 3);
        button_a.setHeight(height_screen / 3);
        View.OnClickListener button_a_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "a");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_a.setOnClickListener(button_a_listener);

        //
        //

        button_b = findViewById(R.id.button_b);
        button_b.setWidth(width_screen / 3);
        button_b.setHeight(height_screen / 3);
        View.OnClickListener button_b_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "b");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_b.setOnClickListener(button_b_listener);

        //
        //

        button_c = findViewById(R.id.button_c);
        button_c.setWidth(width_screen / 3);
        button_c.setHeight(height_screen / 3);
        View.OnClickListener button_c_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "c");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_c.setOnClickListener(button_c_listener);

        //
        //

        button_d = findViewById(R.id.button_d);
        button_d.setWidth(width_screen / 3);
        button_d.setHeight(height_screen / 3);
        View.OnClickListener button_d_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "d");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_d.setOnClickListener(button_d_listener);

        //
        //

        button_e = findViewById(R.id.button_e);
        button_e.setWidth(width_screen / 3);
        button_e.setHeight(height_screen / 3);
        View.OnClickListener button_e_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "e");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_e.setOnClickListener(button_e_listener);

        //
        //

        button_f = findViewById(R.id.button_f);
        button_f.setWidth(width_screen / 3);
        button_f.setHeight(height_screen / 3);
        View.OnClickListener button_f_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "f");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        button_f.setOnClickListener(button_f_listener);

        //
        //

        lastInputs = findViewById(R.id.lastInputs);
        View.OnClickListener lastInputsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Das Array mit den Eingaben an die Activity LastInputs schicken
                lastInputsIntent.putStringArrayListExtra ("inputs", lastInputsArray);
                //Zur Activity LastInputs wechseln
                startActivityForResult(lastInputsIntent, 123);
            }
        };
        lastInputs.setOnClickListener(lastInputsListener);

        //History durch langes Drücken des History-Button löschen

        View.OnLongClickListener lastInputsLongListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int sizeArray = lastInputsArray.size();
                if (sizeArray > 0) {
                    lastInputsArray.clear();
                    if (sizeArray == 1) {
                        Toast.makeText(FrontendInput.this, "Die letzte Eingabe wurde gelöscht!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FrontendInput.this, "Die letzten " + sizeArray + " Eingaben wurden gelöscht!", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        };
        lastInputs.setOnLongClickListener(lastInputsLongListener);

        //
        //

        button_solve = findViewById(R.id.button_solve);
        button_solve.setWidth(width_screen / 3);
        button_solve.setHeight(height_screen / 3);

        View.OnClickListener button_solve_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();
                if (checkEmptyInput()) {
                    Toast.makeText(FrontendInput.this, "Keine leere Eingabe erlaubt!", Toast.LENGTH_SHORT).show();
                } else if (!checkFirstInput()) {
                    Toast.makeText(FrontendInput.this, "Falsches erstes Zeichen!", Toast.LENGTH_SHORT).show();
                } else if (!checkValidInput()) {
                    Toast.makeText(FrontendInput.this, "Fehlerhafte Eingabe!", Toast.LENGTH_SHORT).show();
                }  else if (!checkEmptyInput() && !checkFirstInput() && checkValidInput()) {

                    //Die Eingaben in das Array lastInputsArray speichern

                    String inputExpressionString = inputExpression.getText().toString();
                    int counterExpression=0;
                    if (lastInputsArray.size()==10) {
                        lastInputsArray.remove(0);
                    }
                    for (int i=0; i<lastInputsArray.size();i++) {
                        if (lastInputsArray.get(i).equals(inputExpressionString)) {
                            counterExpression++;
                        }
                    }
                    if (counterExpression==0) {
                        lastInputsArray.add(lastInputsArray.size(), inputExpressionString);
                    }
                    //
                    Intent solution = new Intent(FrontendInput.this,

                            //Hier muss an Stelle von Backend noch definiert werden, an welche Activity die Eingabe übergeben wird

                            FrontendInput.class
                    );



                    solution.putExtra("method", typeMethod);
                    solution.putExtra("input", inputExpression.getText().toString());
                    startActivity(solution);
                    inputExpression.setText("");
                    findViewById(R.id.lastInputs).setVisibility(View.VISIBLE);
                }
            }
        };
        button_solve.setOnClickListener(button_solve_listener);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed () {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Erneutes Drücken beendet die App", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.credits) {
            startActivity(new Intent(FrontendInput.this, Credits.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_vgl) {
            button_solve.setText("2. Ausdruck");
        } else {
            button_solve.setText("Lösen");
        }

        if (id == R.id.nav_wt || getTitle() == "BoolTR") {
            setTitle("Wertetabelle");
            typeMethod = "wt";
        } else if (id == R.id.nav_nf) {
            setTitle("Normalformen");
            typeMethod = "nf";
        } else if (id == R.id.nav_tp) {
            setTitle("Tautologieprüfung");
            typeMethod = "tp";
        } else if (id == R.id.nav_vgl) {
            setTitle("Vergleich");
            typeMethod = "vg";
        } else if (id == R.id.nav_ks) {
            setTitle("Klauselschreibweise");
            typeMethod = "ks";
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean checkEmptyInput () {
        TextView input_expression = findViewById(R.id.inputExpression);
        if (input_expression.getText().toString().equals("")) {
            return true;
        }
        return false;
    }

    // @Override
    public boolean checkFirstInput () {
        String allowedSymbols[] = {"a","b","c","d","e","f","0","1","¬","(",")"};
        String disallowedSymbols[] = {"+","*","→","↔"};
        TextView inputExpression = findViewById(R.id.inputExpression);
        String inputExpressionString = inputExpression.getText().toString();
        String firstSymbol=inputExpressionString.substring(0, 1);
        for (int i=allowedSymbols.length; i>0; i--) {
            if (firstSymbol.equals(allowedSymbols[i-1])) {
                return true;
            }
        }
        for (int j=disallowedSymbols.length; j>0; j--) {
            if (firstSymbol.equals(disallowedSymbols[j-1])) {
                return false;
            }
        }
        return false;
    }

    public boolean checkValidInput () {
        String alphabetSymbols[] = {"a","b","c","d","e","f"};
        String booleanSymbols[] = {"0","1"};
        String operatorsSymbols[] = {"+","*","→","↔"};
        String operators2Symbols[] = {"¬","(",")"};

        TextView inputExpression = findViewById(R.id.inputExpression);
        String inputExpressionString = inputExpression.getText().toString();

        return true;

    }

    public void setCursor() {
        int inputExpressionLength = inputExpression.length();
        inputExpression.setSelection(inputExpressionLength);
    }

    public void setHistoryInvisible() {
        if (inputExpression.length()==10) {
            findViewById(R.id.lastInputs).setVisibility(View.INVISIBLE);
        }
    }
    public void setHistoryVisible() {
        if (inputExpression.length()<10) {
            findViewById(R.id.lastInputs).setVisibility(View.VISIBLE);
        }
    }

    public void vibrate() {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
    }
}