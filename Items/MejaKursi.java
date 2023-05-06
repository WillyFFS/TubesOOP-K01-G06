package Items;

import javax.swing.JOptionPane;
import src.Sim;
import java.util.List;
import java.util.ArrayList;
import src.World;

public class MejaKursi extends NonMakanan {

    private int durasiMakan;

    public MejaKursi(){
        super(50, 3, 3); //harga, panjang, lebar sudah ditetapkan
    }
    public String getNama(){
        return "Meja Kursi";
    }
    public int getdurasiMakan(){
        return durasiMakan;
    }

    public void printListAction(){
        System.out.println("1. Main");
        System.out.println("2. Makan");
        System.out.println("3. Minum");
        System.out.println("4. Berdoa");
    };

    public void doAction(Object... args){
        List<String> currMasakanInv = new ArrayList<>();
        Sim sim = (Sim) args[0];
        for (Item item: sim.getInventory().getMap().keySet()){
            if(item instanceof Makanan){
                currMasakanInv.add(((Makanan) item).getNama());
            }
        }
        String[] arrCurrString = currMasakanInv.toArray(new String[0]);
        String namaMasakan = (String) JOptionPane.showInputDialog(null, "Apa yang ingin dimakan", "Makan", JOptionPane.QUESTION_MESSAGE, null, arrCurrString, arrCurrString[0]);
        if (namaMasakan == null) {
                 JOptionPane.showMessageDialog(null, "You have cancelled this action.", "Makan", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int durasiMakan = 30;
            Thread t = new Thread(()->{
            try{
                    sim.setStatus("Sim sedang makan");
                    System.out.println("Sim sedang makan...");
                    Thread.sleep(3000); //durasiMakan*1000
                }
                catch(InterruptedException e){
                    System.out.println("Proses makan terganggu");
                }
            });
            t.start();
            try{
                t.join();
                sim.getKesejahteraan().setHunger(((Makanan) sim.getInventory().getItem(namaMasakan, 1)).getKekenyangan());
                World.getInstance().addWaktu(durasiMakan*1000);
                // World.getInstance().checkSimTime(durasiMakan);
                sim.tambahWaktuBelumTidur(durasiMakan*1000);
                sim.tambahWaktuBelumBAB(durasiMakan*1000);
                sim.setTimerGantiKerja(durasiMakan*1000);
                sim.resetTimerBelumBab();
                sim.resetWaktuTidurAfterNoSleep();
                sim.tambahDurasiBerkunjung(durasiMakan);
                System.out.println("Proses makan selesai");
            }catch(InterruptedException e){
                System.out.println("Proses makan terganggu");
            }
                }
        
        }

    public void main(int waktu, Sim sim){//nerima waktu mau berapa lama
        sim.setStatus("Sim sedang main");
        System.out.println("Sim sedang main...");
        Thread t = new Thread(()->{
        try{
                Thread.sleep(3000); //1 detik main waktu*1000
            }
            catch(InterruptedException e){
                System.out.println("Proses main terganggu");
            }
        });
        t.start();
        try{
            t.join();
            // sim.getKesejahteraan().setMood(waktu*2); //namabah mood waktu*2
            // sim.getKesejahteraan().setHunger(-waktu); //ngurang kenyang waktu
            System.out.println("Proses main selesai");
            World.getInstance().addWaktu(waktu*1000);
            // World.getInstance().checkSimTime(waktu);
            sim.tambahWaktuBelumTidur(waktu*1000);
            sim.tambahWaktuBelumBAB(waktu*1000); 
            sim.setTimerGantiKerja(waktu*1000);
            sim.resetTimerBelumBab();
            sim.resetWaktuTidurAfterNoSleep();
            sim.tambahDurasiBerkunjung(waktu*1000);
        }catch(InterruptedException e){
            System.out.println("Proses main terganggu");
        }
    }
        
    public void berdoa(Sim sim){
        sim.setStatus("Sim sedang berdoa");
        System.out.println("Sim sedang berdoa...");
        Thread t = new Thread(()->{
        try{
                Thread.sleep(10000); //1 detik berdoa
            }
            catch(InterruptedException e){
                System.out.println("Proses berdoa terganggu");
            }
        });
        t.start();
        try{
            t.join();
            sim.getKesejahteraan().setMood(10*3); //namabah mood 10*3
            sim.getKesejahteraan().setHunger(10); //ngurang kenyang sebanyak waktu
            sim.tambahWaktuBelumTidur(10000);
            sim.tambahWaktuBelumBAB(10000); 
            sim.setTimerGantiKerja(10000);
            sim.resetTimerBelumBab();
            sim.resetWaktuTidurAfterNoSleep();
            sim.tambahDurasiBerkunjung(10000);
            System.out.println("Proses berdoa selesai");
        }catch(InterruptedException e){
            System.out.println("Proses berdoa terganggu");
        }
    }
    public void minum(Sim sim){
        sim.setStatus("Sim sedang minum");
        System.out.println("Sim sedang minum...");
        Thread t = new Thread(()->{
        try{
                Thread.sleep(1000); //1 detik minum
            }
            catch(InterruptedException e){
                System.out.println("Proses minum terganggu");
            }
        });
        t.start();
        try{
            t.join();
            sim.getKesejahteraan().setMood(1); //namabah mood 1
            sim.getKesejahteraan().setHunger(-1); //ngurang kenyang 2
            sim.tambahWaktuBelumTidur(1000);
            sim.tambahWaktuBelumBAB(1000); 
            sim.setTimerGantiKerja(1000);
            sim.resetTimerBelumBab();
            sim.resetWaktuTidurAfterNoSleep();
            sim.tambahDurasiBerkunjung(1000);
            System.out.println("Proses minum selesai");
        }catch(InterruptedException e){
            System.out.println("Proses minum terganggu");
        }
    }
}
