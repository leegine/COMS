head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	PrimaryMarketCskParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quotecheck.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * primary_market_csk�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link PrimaryMarketCskRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link PrimaryMarketCskParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link PrimaryMarketCskParams}��{@@link PrimaryMarketCskRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see PrimaryMarketCskPK 
 * @@see PrimaryMarketCskRow 
 */
public class PrimaryMarketCskParams extends Params implements PrimaryMarketCskRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "primary_market_csk";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = PrimaryMarketCskRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return PrimaryMarketCskRow.TYPE;
  }


  /** 
   * <em>fund_code</em>�J�����̒l 
   */
  public  String  fund_code;

  /** 
   * <em>primary_market_code</em>�J�����̒l 
   */
  public  String  primary_market_code;

  /** 
   * <em>data_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  data_timestamp;

  /** 
   * <em>last_updater</em>�J�����̒l 
   */
  public  String  last_updater;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  private boolean fund_code_is_set = false;
  private boolean fund_code_is_modified = false;
  private boolean primary_market_code_is_set = false;
  private boolean primary_market_code_is_modified = false;
  private boolean data_timestamp_is_set = false;
  private boolean data_timestamp_is_modified = false;
  private boolean last_updater_is_set = false;
  private boolean last_updater_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "fund_code=" + fund_code
      + "," + "primary_market_code=" +primary_market_code
      + "," + "data_timestamp=" +data_timestamp
      + "," + "last_updater=" +last_updater
      + "," + "created_timestamp=" +created_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��PrimaryMarketCskParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public PrimaryMarketCskParams() {
    fund_code_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���PrimaryMarketCskRow�I�u�W�F�N�g�̒l�𗘗p����PrimaryMarketCskParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����PrimaryMarketCskRow�I�u�W�F�N�g 
   */
  public PrimaryMarketCskParams( PrimaryMarketCskRow p_row )
  {
    fund_code = p_row.getFundCode();
    fund_code_is_set = p_row.getFundCodeIsSet();
    fund_code_is_modified = p_row.getFundCodeIsModified();
    primary_market_code = p_row.getPrimaryMarketCode();
    primary_market_code_is_set = p_row.getPrimaryMarketCodeIsSet();
    primary_market_code_is_modified = p_row.getPrimaryMarketCodeIsModified();
    data_timestamp = p_row.getDataTimestamp();
    data_timestamp_is_set = p_row.getDataTimestampIsSet();
    data_timestamp_is_modified = p_row.getDataTimestampIsModified();
    last_updater = p_row.getLastUpdater();
    last_updater_is_set = p_row.getLastUpdaterIsSet();
    last_updater_is_modified = p_row.getLastUpdaterIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      primary_market_code_is_set = true;
      primary_market_code_is_modified = true;
      data_timestamp_is_set = true;
      data_timestamp_is_modified = true;
      last_updater_is_set = true;
      last_updater_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof PrimaryMarketCskRow ) )
       return false;
    return fieldsEqual( (PrimaryMarketCskRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�PrimaryMarketCskRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( PrimaryMarketCskRow row )
  {
    if ( fund_code == null ) {
      if ( row.getFundCode() != null )
        return false;
    } else if ( !fund_code.equals( row.getFundCode() ) ) {
        return false;
    }
    if ( primary_market_code == null ) {
      if ( row.getPrimaryMarketCode() != null )
        return false;
    } else if ( !primary_market_code.equals( row.getPrimaryMarketCode() ) ) {
        return false;
    }
    if ( data_timestamp == null ) {
      if ( row.getDataTimestamp() != null )
        return false;
    } else if ( !data_timestamp.equals( row.getDataTimestamp() ) ) {
        return false;
    }
    if ( last_updater == null ) {
      if ( row.getLastUpdater() != null )
        return false;
    } else if ( !last_updater.equals( row.getLastUpdater() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  (fund_code!=null? fund_code.hashCode(): 0) 
        + (primary_market_code!=null? primary_market_code.hashCode(): 0) 
        + (data_timestamp!=null? data_timestamp.hashCode(): 0) 
        + (last_updater!=null? last_updater.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !data_timestamp_is_set )
			throw new IllegalArgumentException("non-nullable field 'data_timestamp' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("fund_code",fund_code);
		if ( primary_market_code != null )
			map.put("primary_market_code",primary_market_code);
		map.put("data_timestamp",data_timestamp);
		if ( last_updater != null )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( primary_market_code_is_modified )
			map.put("primary_market_code",primary_market_code);
		if ( data_timestamp_is_modified )
			map.put("data_timestamp",data_timestamp);
		if ( last_updater_is_modified )
			map.put("last_updater",last_updater);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if (map.size() == 0) {
			map.put("primary_market_code",primary_market_code);
			if ( data_timestamp_is_set )
				map.put("data_timestamp",data_timestamp);
			map.put("last_updater",last_updater);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
		}
		return map;
	}


  /** 
   * <em>fund_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getFundCode()
  {
    return fund_code;
  }


  /** 
   * <em>fund_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundCodeIsSet() {
    return fund_code_is_set;
  }


  /** 
   * <em>fund_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getFundCodeIsModified() {
    return fund_code_is_modified;
  }


  /** 
   * <em>primary_market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPrimaryMarketCode()
  {
    return primary_market_code;
  }


  /** 
   * <em>primary_market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPrimaryMarketCodeIsSet() {
    return primary_market_code_is_set;
  }


  /** 
   * <em>primary_market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPrimaryMarketCodeIsModified() {
    return primary_market_code_is_modified;
  }


  /** 
   * <em>data_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getDataTimestamp()
  {
    return data_timestamp;
  }


  /** 
   * <em>data_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDataTimestampIsSet() {
    return data_timestamp_is_set;
  }


  /** 
   * <em>data_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDataTimestampIsModified() {
    return data_timestamp_is_modified;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLastUpdater()
  {
    return last_updater;
  }


  /** 
   * <em>last_updater</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsSet() {
    return last_updater_is_set;
  }


  /** 
   * <em>last_updater</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdaterIsModified() {
    return last_updater_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new PrimaryMarketCskPK(fund_code);
  }


  /** 
   * <em>fund_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_fundCode <em>fund_code</em>�J�����̒l������킷5���ȉ���String�l 
   */
  public final void setFundCode( String p_fundCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    fund_code = p_fundCode;
    fund_code_is_set = true;
    fund_code_is_modified = true;
  }


  /** 
   * <em>primary_market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_primaryMarketCode <em>primary_market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setPrimaryMarketCode( String p_primaryMarketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    primary_market_code = p_primaryMarketCode;
    primary_market_code_is_set = true;
    primary_market_code_is_modified = true;
  }


  /** 
   * <em>data_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dataTimestamp <em>data_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setDataTimestamp( java.sql.Timestamp p_dataTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_timestamp = p_dataTimestamp;
    data_timestamp_is_set = true;
    data_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>data_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setDataTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    data_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    data_timestamp_is_set = true;
    data_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updater</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdater <em>last_updater</em>�J�����̒l������킷20���ȉ���String�l 
   */
  public final void setLastUpdater( String p_lastUpdater )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updater = p_lastUpdater;
    last_updater_is_set = true;
    last_updater_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_timestamp") ) {
                    return this.data_timestamp;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    return this.fund_code;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    return this.last_updater;
                }
                break;
            case 'p':
                if ( name.equals("primary_market_code") ) {
                    return this.primary_market_code;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("data_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'data_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.data_timestamp = (java.sql.Timestamp) value;
                    if (this.data_timestamp_is_set)
                        this.data_timestamp_is_modified = true;
                    this.data_timestamp_is_set = true;
                    return;
                }
                break;
            case 'f':
                if ( name.equals("fund_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'fund_code' must be String: '"+value+"' is not." );
                    this.fund_code = (String) value;
                    if (this.fund_code_is_set)
                        this.fund_code_is_modified = true;
                    this.fund_code_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("last_updater") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'last_updater' must be String: '"+value+"' is not." );
                    this.last_updater = (String) value;
                    if (this.last_updater_is_set)
                        this.last_updater_is_modified = true;
                    this.last_updater_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("primary_market_code") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'primary_market_code' must be String: '"+value+"' is not." );
                    this.primary_market_code = (String) value;
                    if (this.primary_market_code_is_set)
                        this.primary_market_code_is_modified = true;
                    this.primary_market_code_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
