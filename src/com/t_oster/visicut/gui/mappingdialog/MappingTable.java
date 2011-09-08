/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.t_oster.visicut.gui.mappingdialog;

import com.t_oster.liblasercut.platform.Util;
import com.t_oster.visicut.gui.beans.EditableTablePanel;
import com.t_oster.visicut.gui.beans.EditableTableProvider;
import com.t_oster.visicut.model.mapping.FilterSet;
import com.t_oster.visicut.model.mapping.Mapping;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author thommy
 */
public class MappingTable extends EditableTablePanel implements EditableTableProvider, ListSelectionListener
{
  
  public MappingTable()
  {
    this.setTableModel(model);
    this.setProvider(this);
    this.addListSelectionListener(this);
  }
  private MappingListModel model = new MappingListModel();
  protected Mapping selectedMapping = null;
  public static final String PROP_SELECTEDMAPPING = "selectedMapping";

  /**
   * Get the value of selectedMapping
   *
   * @return the value of selectedMapping
   */
  public Mapping getSelectedMapping()
  {
    return selectedMapping;
  }

  /**
   * Set the value of selectedMapping
   *
   * @param selectedMapping new value of selectedMapping
   */
  public void setSelectedMapping(Mapping selectedMapping)
  {
    Mapping oldSelectedMapping = this.selectedMapping;
    this.selectedMapping = selectedMapping;
    firePropertyChange(PROP_SELECTEDMAPPING, oldSelectedMapping, selectedMapping);
    if (Util.differ(oldSelectedMapping, selectedMapping))
    {
      if (selectedMapping == null)
      {
        this.clearSelection();
      }
      else
      {
        this.setSelectedRow(mappings.indexOf(selectedMapping));
      }
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent lse)
  {
    int idx = this.getSelectedRow();
    if (idx >= 0)
    {
      this.setSelectedMapping(mappings.get(idx));
    }
  }
  
  
  
  protected List<Mapping> mappings = null;
  public static final String PROP_MAPPINGS = "mappings";

  /**
   * Get the value of mappings
   *
   * @return the value of mappings
   */
  public List<Mapping> getMappings()
  {
    return mappings;
  }

  /**
   * Set the value of mappings
   *
   * @param mappings new value of mappings
   */
  public void setMappings(List<Mapping> mappings)
  {
    List<Mapping> oldMappings = this.mappings;
    this.mappings = mappings;
    firePropertyChange(PROP_MAPPINGS, oldMappings, mappings);
    this.model.setMappings(mappings);
    this.setObjects((List) mappings);
  }

  public MappingListModel getModel()
  {
    return this.model;
  }
  
  public Object getNewInstance()
  {
    Mapping m = new Mapping();
    m.setFilterSet(new FilterSet());
    m.setProfileName("cut line");
    return m;
  }

  public Object editObject(Object o)
  {
    return o;
  }
}
