package uvsq.m1.gl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void testPersonnel() {
    	
    	Personnel p1 = new Personnel.Builder("maryeme", "elmchichi", "ing�nieure").build();
    	
    	assertEquals("maryeme", p1.getNom());
    	
    }
    @Test
    public void testAnnuaireComplet() {

        Annuaire a = Annuaire.getInstance();
        String tel = new String("0687359366");
        Groupe g1 = new Groupe("DIRECTEUR");
        Groupe g2 = new Groupe("RH");
        List<String> telist = new ArrayList<>();
        telist.add(tel);
        telist.add("078895996525");
        Personnel m1 = new Personnel.Builder("maryeme", "elmchichi", "ing�nieure").updatePhoneList(telist).build();
        Personnel m2 = new Personnel.Builder("employer", "capgemini", "d�vloppeur").updatePhoneList(telist).build();

        g1.ajoutMembre(m1);
        g1.ajoutMembre(m2);
        g2.ajoutMembre(m1);
        a.addEquipe(m1);
        a.addEquipe(new Groupe("Groupe1"));
        a.addEquipe(new Groupe("Groupe2"));
        a.addEquipe(g1);
        a.addEquipe(new Groupe("Groupe3"));
        a.addEquipe(new Groupe("Groupe4"));
        a.addEquipe(g2);

        System.out.println("creer");
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("./maryeme")))) {
            out.writeObject(Annuaire.getInstance());
        }
        catch(IOException ioe){

        }
        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("./maryeme")))) {
            Annuaire  test = (Annuaire) in.readObject();
            for(Team t : test) {

               t.print();
            }


        }
        catch(ClassNotFoundException | IOException e){

        }

    }}
