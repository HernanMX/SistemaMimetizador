package sistemamimetizador;
//librerias 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
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

public class InterfazUsu extends JFrame {

    JButton btnAgregar, btnEliminar, btnActualizar;
    JPanel panel, panelSec, panelTab1, panelTAb2, panelTAb3;
    JLabel lbltitulo, lblTemp, lblHumedad, lblLum, lblHora, lblFecha,
            etiqueta1;
    String nom;
    JTextArea texto;
    JTextField txtTemp, txtHumedad, txtLum, txtHora, txtFecha,
            txtData, txtFec, txtTabla;
    JTabbedPane panelPest;
    JTable tabla;
    DefaultTableModel dtm;
    /*String columnas[] = {"Mensaje"};
    String datos[][] = {};*/
    JScrollPane scroll,scrollTextArea;
    Date d;
    ArrayList<String> sms;
    //int contCaracter;

    public InterfazUsu() {

        super("Sistema Mimetizador");
        this.setLayout(null);
        crear();
        armarPanelTAb();
        armarPanelOeste();
        armarVentana();
        lanzar();

    }

    private void crear() {
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnActualizar = new JButton("Actualizar");
        texto = new JTextArea();
        panel = new JPanel(null);
        panelSec = new JPanel(new BorderLayout());
        panelPest = new JTabbedPane();
        panelTab1 = new JPanel();
        panelTAb2 = new JPanel();
        panelTAb3 = new JPanel();
        d = new Date();
        sms = new ArrayList();
        etiqueta1 = new JLabel("panel uno", SwingConstants.CENTER);
        lblFecha = new JLabel("FECHA: " + d.getDate()+ "/" + (d.getMonth()+1) +
                             "/"+ (d.getYear()+1900),SwingConstants.CENTER);
        lblHora = new JLabel("HORA: " + d.getHours() + ":" + d.getMinutes()
                             ,SwingConstants.CENTER);
        scrollTextArea = new JScrollPane(texto);
    }

    void armarPanelTAb() {
        panelPest.setBackground(Color.WHITE);
        panelTab1.setBackground(Color.WHITE);
        panelTAb2.setBackground(Color.WHITE);
        panelTAb3.setBackground(Color.white);
        panelTab1.setLayout(new GridLayout(2, 1));
        panelTab1.add(lblFecha,0);
        panelTab1.add(lblHora,1);
        panelPest.addTab("Fecha y Hora", null, panelTab1, "Primer panel");
        panelPest.addTab("Medidas", null, panelTAb2, "Segundo panel");
        panelPest.addTab("Tabla de mensajes", null, panelTAb3, "Tercer panel");
    }

    void armarPanelOeste() {
        panel.setLayout(null);
        scrollTextArea.setBounds(50, 100, 300, 180);
        btnAgregar.setBounds(50, 300, 100, 15);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (texto.getText().length() <= 128 ){
                    sms.add(texto.getText());
                    System.out.println(sms);
                }else{
                    JOptionPane.showMessageDialog(null,"Intenta mostrar un mensaje demasiado grande",
                                                  "Error",JOptionPane.ERROR_MESSAGE,null);
       
                }
                texto.setText(null);
            }
        });

        btnAgregar.setBackground(new Color(50, 141, 182));
        btnActualizar.setBackground(new Color(50, 141, 182));
        btnEliminar.setBackground(new Color(50, 141, 182));
        btnAgregar.setForeground(Color.WHITE);
        btnEliminar.setForeground(Color.WHITE);
        btnActualizar.setForeground(Color.WHITE);
        panel.add(btnAgregar);
        panel.add(scrollTextArea);
        //panel.add(btnActualizar);
        //panel.add(btnEliminar);

    }

    void armarVentana() {
        panelSec.add(panelPest);
        panelSec.setBounds(400, 100, 450, 200);
        panel.setBounds(0, 0, 400, 550);
        this.setBackground(Color.WHITE);
        add(panelSec, BorderLayout.WEST);
        add(panel);
        //Falta panel para escribir los mensajes?
        //NO ya no, esperame poquito, si quieres yo lo acomodo y tu le pones funciones arre

    }

    void lanzar() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 550);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}

