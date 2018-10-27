package sistemamimetizador;
//libreria para la comunicacion con arduino
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_MultiMessage;

//importacion de los elementos necesarios para creacion de interfaz
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ResourceBundle.Control;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class InterfazUsu extends JFrame {
    //Declaracion de elementos para crear interfaz grafica
    JButton btnAgregar, btnEliminar, btnActualizar, btnSensores;
    JPanel panel, panelSec, panelTab1, panelTab2, panelTab3;
    JLabel lbltitulo, lblTemp, lblHumedad, lblLum, lblHora, lblFecha,
            etiqueta1;
    String nom;
    JTextArea texto;
    JTextField txtTemp, txtHumedad, txtLum, txtHora, txtFecha,
            txtData, txtFec, txtTabla;
    JTabbedPane panelPest;
    JTable tabla;
    DefaultTableModel dtm;
    String columnas[] = {"No.", "Mensaje", "Fecha y hora"};
    String datos[][] = {};
    JScrollPane scroll, scrollTextArea;
    //Instanciamos un objeto tipo Date, para obtener la fecha de creacion de los mensajes
    Date d;
    int contador, columna, fila,cont;
    // Instanciamos el objeto arduino el cual nos ayudara con la comunicacion de java con arduino
    static PanamaHitek_Arduino arduino;
    static SerialPortEventListener listener;
    static PanamaHitek_MultiMessage multi;
    //OBjetos utilizados para lectura y escritura en archivo de texto
    static Scanner lector,lector1;
    static String entrada = "",entrada1="";
    FileWriter fichero = null;
    PrintWriter pw = null;
 
    
    public InterfazUsu() {
        super("Sistema Mimetizador");
        this.setLayout(null);
        crear();
        try {
            //Inicia comunicacion con arduino
            arduino.arduinoRXTX("/dev/ttyACM0", 9600, listener);
        } catch (ArduinoException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        carDat();
        armarPanelTAb();
        armarPanelOeste();
        armarVentana();
        lanzar();
    }
    
    void carDat(){
        try {
            //Se cargan los datos almacenados en el archivo de texto a la tabla de mensajes
            lector = new Scanner(new FileInputStream("/home/hernan_gonzalez/NetBeansProjects/SistemaMimetizador/SIstemaMimetizador/src/Texto/Cadena.txt"));
            while (lector.hasNext()) {
                entrada = lector.nextLine();
                String message = entrada;
                String[] men = message.split("-");
                contador++;
                Object[] datosEntrada = {contador, men[0], men[1]};
                String sms = "";
                dtm.addRow(datosEntrada);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LEER ARCHIVO", "Sistema Mimetizador", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crear() {
        //Metodo para crear interfaz de usuario
        //Se inicializan los elementos necesarios para la construccion de interfaz
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");
        btnSensores = new JButton("Actualizar");
        texto = new JTextArea();
        panel = new JPanel(null);
        panelSec = new JPanel(new BorderLayout());
        panelPest = new JTabbedPane();
        panelTab1 = new JPanel();
        panelTab2 = new JPanel();
        panelTab3 = new JPanel();
        dtm = new DefaultTableModel(datos, columnas);
        tabla = new JTable(dtm);
        tabla.setAutoCreateRowSorter(true);
        tabla.setShowGrid(true);
        tabla.setPreferredScrollableViewportSize(new Dimension(200, 150));
        scroll = new JScrollPane(tabla);
        d = new Date();
        etiqueta1 = new JLabel("panel uno", SwingConstants.CENTER);
        //Obtenemos la fecha y hora actual del sistema.
        lblFecha = new JLabel("FECHA: " + d.getDate() + "/" + (d.getMonth() + 1)
                + "/" + (d.getYear() + 1900), SwingConstants.CENTER);
        lblHora = new JLabel("HORA: " + d.getHours() + ":" + d.getMinutes()
                + ":" + d.getSeconds(), SwingConstants.CENTER);
        scrollTextArea = new JScrollPane(texto);
        contador = 0;
        cont=1;
        fila = 0;
        lblTemp = new JLabel();
        lblHumedad = new JLabel();
        lblLum = new JLabel();
        //SE inicializan los elementos necesarios para la comunicacion con arduino
        arduino = new PanamaHitek_Arduino();
        multi = new PanamaHitek_MultiMessage(3, arduino);
        listener = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent spe) {
                try {
                    if (multi.dataReceptionCompleted()) {
//                        lblTemp.setText("Temperatura: " + multi.getMessage(0) + "°C");
//                        lblHumedad.setText("Humedad: " + multi.getMessage(1) + "%");
//                        lblLum.setText("Luminosidad: " + multi.getMessage(2) + "");
                    }
                } catch (ArduinoException ex) {
                    Logger.getLogger(InterfazUsu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SerialPortException ex) {
                    Logger.getLogger(InterfazUsu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        /////////////////////////////////////////
    }

    void armarPanelTAb() {
        //Construccion de panel que muestra la fecha y hora
        panelPest.setBackground(Color.WHITE);
        panelTab1.setBackground(Color.WHITE);
        panelTab2.setBackground(Color.WHITE);
        panelTab3.setBackground(Color.white);
        panelTab3.setLayout(null);
        panelTab1.setLayout(new GridLayout(2, 1));
        panelTab2.setLayout(null);
        panelTab1.add(lblFecha, 0);
        panelTab1.add(lblHora, 1);
        scroll.setBounds(0, 0, 300, 180);
        panelTab3.add(scroll);
        btnEliminar.setBounds(330, 20, 100, 30);
        panelTab3.add(btnEliminar);
        btnActualizar.setBounds(330, 60, 100, 30);
        panelTab3.add(btnActualizar);
        lblTemp.setBounds(30, 30, 200, 10);
        lblHumedad.setBounds(30, 65, 150, 10);
        lblLum.setBounds(30, 95, 150, 10);
        btnSensores.setBounds(300, 60, 120, 30);
        panelTab2.add(lblTemp);
        panelTab2.add(lblHumedad);
        panelTab2.add(lblLum);
        //panelTab2.add(btnSensores);
        panelPest.addTab("Fecha y Hora", null, panelTab1, "Primer panel");
        ///panelPest.addTab("Medidas", null, panelTab2, "Segundo panel");
        panelPest.addTab("Tabla de mensajes", null, panelTab3, "Tercer panel");
        System.out.println(arduino.getSerialPorts());

    }

    void armarPanelOeste() {
        //Construccion del panel que muestra la tabla
        panel.setLayout(null);
        scrollTextArea.setBounds(50, 125, 300, 178);
        btnAgregar.setBounds(150, 330, 100, 30);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date actualizard = new Date();
                if (!texto.getText().isEmpty()) {
                    if (texto.getText().length() <= 128) {
                        contador++;
                        Object[] registro = {contador, texto.getText(), lblFecha.getText() + " " + lblHora.getText()};
                        dtm.addRow(registro);
                        lblFecha.setText("FECHA: " + actualizard.getDate() + "/"
                                + (actualizard.getMonth() + 1)
                                + "/" + (actualizard.getYear() + 1900));
                        lblHora.setText("HORA: " + actualizard.getHours() + ":"
                                + actualizard.getMinutes() + ":" + actualizard.getSeconds());
                        try {
                            String sms = "";
                            sms += contador + "," + texto.getText() + " " + lblFecha.getText()
                                    + lblHora.getText();
                            arduino.sendData(sms);

                        } catch (ArduinoException ex) {
                            Logger.getLogger(InterfazUsu.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SerialPortException ex) {
                            Logger.getLogger(InterfazUsu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Intenta mostrar un mensaje demasiado grande",
                                "Error", JOptionPane.ERROR_MESSAGE, null);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El mensje que desea agregar esta vacio",
                            "Error", JOptionPane.ERROR_MESSAGE, null);
                }
                texto.setText(null);
                try {
                    fichero = new FileWriter("/home/hernan_gonzalez/NetBeansProjects/SistemaMimetizador/SIstemaMimetizador/src/Texto/Cadena.txt");
                    pw = new PrintWriter(fichero);

                    for (int i = 0; i < dtm.getRowCount(); i++) {
                        pw.println(dtm.getValueAt(i, 1) + "-" + dtm.getValueAt(i, 2));
                    }

                } catch (Exception eb) {
                    System.out.println("Error");
                } finally {
                    try {
                        // Nuevamente aprovechamos el finally para 
                        // asegurarnos que se cierra el fichero.
                        if (null != fichero) {
                            fichero.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

            }
        });
        btnAgregar.setBackground(new Color(50, 141, 182));
        btnActualizar.setBackground(new Color(50, 141, 182));
        //Al actualizar la informacion de la tabla se envia nuevamente a arduino la informacion
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
            lector1 = new Scanner(new FileInputStream("/home/hernan_gonzalez/NetBeansProjects/SistemaMimetizador/SIstemaMimetizador/src/Texto/Cadena.txt"));
            while (lector1.hasNext()) {
                entrada1 = lector1.nextLine();
                String message = entrada1;
                String[] men = message.split("-");
                cont++;
                //Object[] datosEntrada = {contador, men[0], men[1]};
                 String sms = "";
                System.out.println(cont);
                            sms += cont + "," + men[0]+" "+men[1];
                            arduino.sendData(sms);
                //dtm.addRow(datosEntrada);

            }
            //arduino.sendData(sms);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL LEER ARCHIVO", "Sistema Mimetizador", JOptionPane.ERROR_MESSAGE);
        }
            }

        });
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                texto.setText(dtm.getValueAt(tabla.getSelectedRow(), 1).toString());
                fila = tabla.getSelectedRow();
            }

        });
        btnEliminar.setBackground(new Color(50, 141, 182));
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int count;
                try {
                    
                    String sms = "";
                            sms += (tabla.getSelectedRow()+1) + "," +"";
                    arduino.sendData(sms);
                    dtm.removeRow(tabla.getSelectedRow());
                    texto.setText("");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado "
                            + "ningun mensaje", "Error",
                            JOptionPane.ERROR_MESSAGE, null);
                }
                try {
                    fichero = new FileWriter("/home/hernan_gonzalez/NetBeansProjects/SistemaMimetizador/SIstemaMimetizador/src/Texto/Cadena.txt");
                    pw = new PrintWriter(fichero);

                    for (int i = 0; i < dtm.getRowCount(); i++) {
                        pw.println(dtm.getValueAt(i, 1) + "-" + dtm.getValueAt(i, 2));
                    }

                } catch (Exception eb) {
                    System.out.println("Error");
                } finally {
                    try {
                        // Nuevamente aprovechamos el finally para 
                        // asegurarnos que se cierra el fichero.
                        if (null != fichero) {
                            fichero.close();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

        });
        btnAgregar.setForeground(Color.WHITE);
        btnEliminar.setForeground(Color.WHITE);
        btnActualizar.setForeground(Color.WHITE);
        panel.add(btnAgregar);
        panel.add(scrollTextArea);
    }

    void armarVentana() {
        panelSec.add(panelPest);
        panelSec.setBounds(400, 100, 450, 200);
        panel.setBounds(0, 0, 400, 550);
        this.setBackground(Color.WHITE);
        add(panelSec, BorderLayout.WEST);
        add(panel);
    }

    void lanzar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
