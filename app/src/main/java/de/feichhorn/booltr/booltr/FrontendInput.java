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
            buttonOne,
            buttonDelete,
            buttonDeleteAll,
            buttonPlus,
            buttonMultiply,
            buttonNot,
            buttonArrow,
            buttonDarrow,
            buttonBracket1,
            buttonBracket2,
            buttonA,
            buttonB,
            buttonC,
            buttonD,
            buttonE,
            buttonF,
            buttonSolve;
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
        buttonOne = findViewById(R.id.buttonOne);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonNot = findViewById(R.id.buttonNot);
        buttonArrow = findViewById(R.id.buttonArrow);
        buttonDarrow = findViewById(R.id.buttonDarrow);
        buttonBracket1 = findViewById(R.id.buttonBracket1);
        buttonBracket2 = findViewById(R.id.buttonBracket2);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        buttonE = findViewById(R.id.buttonE);
        buttonF = findViewById(R.id.buttonF);
        buttonSolve = findViewById(R.id.buttonSolve);
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

        inputExpression = findViewById(R.id.inputExpression);

        //
        //

        //Definition der Eingabefelder des Frontends -> Input-Buttons, Delete-Button, Solve-Button, History-Button

        //
        //

        buttonZero = findViewById(R.id.buttonZero);
        //Maße der Buttons kontrollieren
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

        buttonOne = findViewById(R.id.buttonOne);
        View.OnClickListener buttonOneListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "1");
                vibrate();
                setHistoryInvisible();
                setCursor();
            }
        };
        buttonOne.setOnClickListener(buttonOneListener);

        //
        //

        buttonDeleteAll = findViewById(R.id.buttonDelete);
        final View.OnLongClickListener buttonDeleteLongListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                inputExpression.setText("");
                setHistoryVisible();
                return true;
            }
        };
        buttonDeleteAll.setOnLongClickListener(buttonDeleteLongListener);

        //

        buttonDelete = findViewById(R.id.buttonDelete);

        View.OnClickListener buttonDeleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputExpressionLength = inputExpression.length();
                if (inputExpression.getSelectionStart() != 0) {
                    if (inputExpression.getSelectionStart() == inputExpressionLength) {
                        if (inputExpressionLength != 0) {
                            inputExpression.setSelection(inputExpressionLength);
                            inputExpression.setCursorVisible(true);
                            String inputExpressionString = inputExpression.getText().toString();
                            inputExpressionString = inputExpressionString.substring(0, inputExpressionLength - 1);
                            inputExpression.setText(inputExpressionString);
                            setCursor();
                            vibrate();
                        } else {

                        }
                    } else {
                        int positionCursor = inputExpression.getSelectionStart();
                        inputExpressionLength = inputExpression.length();
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
        buttonDelete.setOnClickListener(buttonDeleteListener);

        //
        //

        buttonPlus = findViewById(R.id.buttonPlus);
        View.OnClickListener buttonPlusListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "+");
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(50);
                int inputExpressionLength = inputExpression.length();
                inputExpression.setSelection(inputExpressionLength);
                if (inputExpressionLength == 15) {
                    findViewById(R.id.lastInputs).setVisibility(View.INVISIBLE);
                }
                inputExpression.setSelection(inputExpressionLength);
            }
        };
        buttonPlus.setOnClickListener(buttonPlusListener);

        //
        //

        buttonMultiply = findViewById(R.id.buttonMultiply);
        View.OnClickListener buttonMultiplyListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "*");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonMultiply.setOnClickListener(buttonMultiplyListener);

        //
        //

        buttonNot = findViewById(R.id.buttonNot);
        View.OnClickListener buttonNotListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "¬");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonNot.setOnClickListener(buttonNotListener);

        //
        //

        buttonArrow = findViewById(R.id.buttonArrow);
        View.OnClickListener buttonArrowListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "→");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonArrow.setOnClickListener(buttonArrowListener);

        //
        //

        buttonDarrow = findViewById(R.id.buttonDarrow);
        View.OnClickListener buttonDarrowListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "↔");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonDarrow.setOnClickListener(buttonDarrowListener);

        //
        //

        buttonBracket1 = findViewById(R.id.buttonBracket1);
        View.OnClickListener buttonBracket1Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "(");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonBracket1.setOnClickListener(buttonBracket1Listener);

        //
        //

        buttonBracket2 = findViewById(R.id.buttonBracket2);
        View.OnClickListener buttonBracket2Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + ")");
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(50);
                int inputExpressionLength = inputExpression.length();
                inputExpression.setSelection(inputExpressionLength);
                if (inputExpressionLength == 15) {
                    findViewById(R.id.lastInputs).setVisibility(View.INVISIBLE);
                }
                inputExpression.setSelection(inputExpressionLength);
            }
        };
        buttonBracket2.setOnClickListener(buttonBracket2Listener);

        //
        //

        buttonA = findViewById(R.id.buttonA);
        View.OnClickListener buttonAListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "a");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonA.setOnClickListener(buttonAListener);

        //
        //

        buttonB = findViewById(R.id.buttonB);
        View.OnClickListener buttonBListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "b");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonB.setOnClickListener(buttonBListener);

        //
        //

        buttonC = findViewById(R.id.buttonC);
        View.OnClickListener buttonCListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "c");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonC.setOnClickListener(buttonCListener);

        //
        //

        buttonD = findViewById(R.id.buttonD);
        View.OnClickListener buttonDListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "d");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonD.setOnClickListener(buttonDListener);

        //
        //

        buttonE = findViewById(R.id.buttonE);
        View.OnClickListener buttonEListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "e");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonE.setOnClickListener(buttonEListener);

        //
        //

        buttonF = findViewById(R.id.buttonF);
        View.OnClickListener buttonFListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputExpression.setText(inputExpression.getText() + "f");
                vibrate();
                setCursor();
                setHistoryInvisible();
            }
        };
        buttonF.setOnClickListener(buttonFListener);

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

        buttonSolve = findViewById(R.id.buttonSolve);

        View.OnClickListener buttonSolveListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();
                if (checkEmptyInput() == true) {
                    Toast.makeText(FrontendInput.this, "Keine leere Eingabe erlaubt!", Toast.LENGTH_SHORT).show();
                } else if (checkFirstInput() == false) {
                    Toast.makeText(FrontendInput.this, "Falsches erstes Zeichen!", Toast.LENGTH_SHORT).show();
                } else if (checkValidInput() == false) {
                    Toast.makeText(FrontendInput.this, "Fehlerhafte Eingabe!", Toast.LENGTH_SHORT).show();
                }  else if (checkEmptyInput() == false && checkFirstInput() == true && checkValidInput() == true) {

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

                            Credits.class);



                    solution.putExtra("method", typeMethod);
                    solution.putExtra("input", inputExpression.getText().toString());
                    startActivity(solution);
                    inputExpression.setText("");
                    findViewById(R.id.lastInputs).setVisibility(View.VISIBLE);
                }
            }
        };
        buttonSolve.setOnClickListener(buttonSolveListener);
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
            buttonSolve.setText("2. Ausdruck");
        } else {
            buttonSolve.setText("Lösen");
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