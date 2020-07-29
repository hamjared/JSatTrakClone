package GUI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import objects.Satellite;

public class SatelliteListModel extends AbstractListModel<Object>{
	
	private static final long serialVersionUID = 3212778671631917814L;
	private List<Satellite> satellites = new ArrayList<Satellite>();

	@Override
	public int getSize() {
		return satellites.size();
	}

	@Override
	public Object getElementAt(int index) {
		return satellites.get(index);
	}
	
	public List<Satellite> getList() {
		return satellites;
	}

}
