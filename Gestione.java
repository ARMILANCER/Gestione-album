package GestioneAlbum;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;
public class Gestione {
	private byte[] buffer;
	private Vector<Integer> valueVec;
	public void insertData(Album album) {
		valueVec = album.getValueVec();
		try (RandomAccessFile raf = new RandomAccessFile("dati.dat","rw")){
			raf.seek(raf.length());
			raf.write(album.getSB().getBytes());
			for (Integer e : valueVec) {
				try {
					System.out.println(e);
					raf.writeInt(e);
					for (int i = 0; i < (20 - Integer.BYTES); i++) {
						raf.writeByte('\0');
					}
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
			raf.writeByte('\n');
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	// index[0] -> element, index[1] operation 
 	public void extractData(){
		try (RandomAccessFile raf = new RandomAccessFile("dati.dat","r")){
		buffer = new byte[20];
		raf.read(buffer);
		System.out.println(new String(buffer, "UTF-8"));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void readAll() {
		byte[] data;
		try(RandomAccessFile raf = new RandomAccessFile("dati.dat","r")){
			data = new byte[(int)raf.length()];
			raf.read(data);
			String text = new String(data,"UTF-8");
			System.out.println(text);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
