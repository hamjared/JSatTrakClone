package GUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

import objects.Satellite;

public class SatelliteListModel extends AbstractListModel{
	
	
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
