package it.univpm.OpenWeatherApp.filtraggio;

import java.util.ArrayList;

public class Filter {
    private ArrayList<String> citta = new ArrayList<String>();
    private String param;
    private String value;
    private int periodo;


    /**
     * Costruttore della classe.
     * @param citta è un ArrayList di Stringhe contenenti i nomi delle città che si vogliono filtrare.
     * @param param parametro per cui si vuole effettuare il filtraggio.
     * @param value valore max o min di param.(pressione)
     * @param periodo periodo in cui si vuole fare la statistica.
     */
    public Filter(ArrayList<String> citta, String param, String value, int periodo) {
        this.citta = citta;
        this.param = param;
        this.value = value;
        this.periodo = periodo;
    }


}
