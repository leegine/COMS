head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.24.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	RlsPriceCondParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.rlsgateway.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * rls_price_cond�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link RlsPriceCondRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link RlsPriceCondParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link RlsPriceCondParams}��{@@link RlsPriceCondRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see RlsPriceCondPK 
 * @@see RlsPriceCondRow 
 */
public class RlsPriceCondParams extends Params implements RlsPriceCondRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "rls_price_cond";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = RlsPriceCondRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return RlsPriceCondRow.TYPE;
  }


  /** 
   * <em>cond_ord_id</em>�J�����̒l 
   */
  public  long  cond_ord_id;

  /** 
   * <em>price</em>�J�����̒l 
   */
  public  long  price;

  /** 
   * <em>direction</em>�J�����̒l 
   */
  public  long  direction;

  /** 
   * <em>prod_id</em>�J�����̒l 
   */
  public  long  prod_id;

  /** 
   * <em>market_id</em>�J�����̒l 
   */
  public  long  market_id;

  private boolean cond_ord_id_is_set = false;
  private boolean cond_ord_id_is_modified = false;
  private boolean price_is_set = false;
  private boolean price_is_modified = false;
  private boolean direction_is_set = false;
  private boolean direction_is_modified = false;
  private boolean prod_id_is_set = false;
  private boolean prod_id_is_modified = false;
  private boolean market_id_is_set = false;
  private boolean market_id_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "cond_ord_id=" + cond_ord_id
      + "," + "price=" +price
      + "," + "direction=" +direction
      + "," + "prod_id=" +prod_id
      + "," + "market_id=" +market_id
      + "}";
  }


  /** 
   * �l�����ݒ��RlsPriceCondParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public RlsPriceCondParams() {
    cond_ord_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���RlsPriceCondRow�I�u�W�F�N�g�̒l�𗘗p����RlsPriceCondParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����RlsPriceCondRow�I�u�W�F�N�g 
   */
  public RlsPriceCondParams( RlsPriceCondRow p_row )
  {
    cond_ord_id = p_row.getCondOrdId();
    cond_ord_id_is_set = p_row.getCondOrdIdIsSet();
    cond_ord_id_is_modified = p_row.getCondOrdIdIsModified();
    price = p_row.getPrice();
    price_is_set = p_row.getPriceIsSet();
    price_is_modified = p_row.getPriceIsModified();
    direction = p_row.getDirection();
    direction_is_set = p_row.getDirectionIsSet();
    direction_is_modified = p_row.getDirectionIsModified();
    prod_id = p_row.getProdId();
    prod_id_is_set = p_row.getProdIdIsSet();
    prod_id_is_modified = p_row.getProdIdIsModified();
    market_id = p_row.getMarketId();
    market_id_is_set = p_row.getMarketIdIsSet();
    market_id_is_modified = p_row.getMarketIdIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      price_is_set = true;
      price_is_modified = true;
      direction_is_set = true;
      direction_is_modified = true;
      prod_id_is_set = true;
      prod_id_is_modified = true;
      market_id_is_set = true;
      market_id_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof RlsPriceCondRow ) )
       return false;
    return fieldsEqual( (RlsPriceCondRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�RlsPriceCondRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( RlsPriceCondRow row )
  {
    if ( cond_ord_id != row.getCondOrdId() )
      return false;
    if ( price != row.getPrice() )
      return false;
    if ( direction != row.getDirection() )
      return false;
    if ( prod_id != row.getProdId() )
      return false;
    if ( market_id != row.getMarketId() )
      return false;
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) cond_ord_id)
        + ((int) price)
        + ((int) direction)
        + ((int) prod_id)
        + ((int) market_id)
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !price_is_set )
			throw new IllegalArgumentException("non-nullable field 'price' must be set before inserting.");
		if ( !direction_is_set )
			throw new IllegalArgumentException("non-nullable field 'direction' must be set before inserting.");
		if ( !prod_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'prod_id' must be set before inserting.");
		if ( !market_id_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_id' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("cond_ord_id",new Long(cond_ord_id));
		map.put("price",new Long(price));
		map.put("direction",new Long(direction));
		map.put("prod_id",new Long(prod_id));
		map.put("market_id",new Long(market_id));
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( price_is_modified )
			map.put("price",new Long(price));
		if ( direction_is_modified )
			map.put("direction",new Long(direction));
		if ( prod_id_is_modified )
			map.put("prod_id",new Long(prod_id));
		if ( market_id_is_modified )
			map.put("market_id",new Long(market_id));
		if (map.size() == 0) {
			if ( price_is_set )
				map.put("price",new Long(price));
			if ( direction_is_set )
				map.put("direction",new Long(direction));
			if ( prod_id_is_set )
				map.put("prod_id",new Long(prod_id));
			if ( market_id_is_set )
				map.put("market_id",new Long(market_id));
		}
		return map;
	}


  /** 
   * <em>cond_ord_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getCondOrdId()
  {
    return cond_ord_id;
  }


  /** 
   * <em>cond_ord_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCondOrdIdIsSet() {
    return cond_ord_id_is_set;
  }


  /** 
   * <em>cond_ord_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCondOrdIdIsModified() {
    return cond_ord_id_is_modified;
  }


  /** 
   * <em>price</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getPrice()
  {
    return price;
  }


  /** 
   * <em>price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceIsSet() {
    return price_is_set;
  }


  /** 
   * <em>price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPriceIsModified() {
    return price_is_modified;
  }


  /** 
   * <em>direction</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getDirection()
  {
    return direction;
  }


  /** 
   * <em>direction</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDirectionIsSet() {
    return direction_is_set;
  }


  /** 
   * <em>direction</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDirectionIsModified() {
    return direction_is_modified;
  }


  /** 
   * <em>prod_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getProdId()
  {
    return prod_id;
  }


  /** 
   * <em>prod_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProdIdIsSet() {
    return prod_id_is_set;
  }


  /** 
   * <em>prod_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProdIdIsModified() {
    return prod_id_is_modified;
  }


  /** 
   * <em>market_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getMarketId()
  {
    return market_id;
  }


  /** 
   * <em>market_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsSet() {
    return market_id_is_set;
  }


  /** 
   * <em>market_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketIdIsModified() {
    return market_id_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new RlsPriceCondPK(cond_ord_id);
  }


  /** 
   * <em>cond_ord_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_condOrdId <em>cond_ord_id</em>�J�����̒l������킷long�l
   */
  public final void setCondOrdId( long p_condOrdId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    cond_ord_id = p_condOrdId;
    cond_ord_id_is_set = true;
    cond_ord_id_is_modified = true;
  }


  /** 
   * <em>price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_price <em>price</em>�J�����̒l������킷long�l
   */
  public final void setPrice( long p_price )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    price = p_price;
    price_is_set = true;
    price_is_modified = true;
  }


  /** 
   * <em>direction</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_direction <em>direction</em>�J�����̒l������킷long�l
   */
  public final void setDirection( long p_direction )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    direction = p_direction;
    direction_is_set = true;
    direction_is_modified = true;
  }


  /** 
   * <em>prod_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_prodId <em>prod_id</em>�J�����̒l������킷long�l
   */
  public final void setProdId( long p_prodId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    prod_id = p_prodId;
    prod_id_is_set = true;
    prod_id_is_modified = true;
  }


  /** 
   * <em>market_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketId <em>market_id</em>�J�����̒l������킷long�l
   */
  public final void setMarketId( long p_marketId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_id = p_marketId;
    market_id_is_set = true;
    market_id_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'c':
                if ( name.equals("cond_ord_id") ) {
                    return new Long( cond_ord_id );
                }
                break;
            case 'd':
                if ( name.equals("direction") ) {
                    return new Long( direction );
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    return new Long( market_id );
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    return new Long( price );
                }
                else if ( name.equals("prod_id") ) {
                    return new Long( prod_id );
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
                if ( name.equals("cond_ord_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'cond_ord_id' must be Long: '"+value+"' is not." );
                    this.cond_ord_id = ((Long) value).longValue();
                    if (this.cond_ord_id_is_set)
                        this.cond_ord_id_is_modified = true;
                    this.cond_ord_id_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("direction") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'direction' must be Long: '"+value+"' is not." );
                    this.direction = ((Long) value).longValue();
                    if (this.direction_is_set)
                        this.direction_is_modified = true;
                    this.direction_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'market_id' must be Long: '"+value+"' is not." );
                    this.market_id = ((Long) value).longValue();
                    if (this.market_id_is_set)
                        this.market_id_is_modified = true;
                    this.market_id_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("price") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'price' must be Long: '"+value+"' is not." );
                    this.price = ((Long) value).longValue();
                    if (this.price_is_set)
                        this.price_is_modified = true;
                    this.price_is_set = true;
                    return;
                }
                else if ( name.equals("prod_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'prod_id' must be Long: '"+value+"' is not." );
                    this.prod_id = ((Long) value).longValue();
                    if (this.prod_id_is_set)
                        this.prod_id_is_modified = true;
                    this.prod_id_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
