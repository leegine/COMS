head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	QuoteMonitorProductDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.quoteadaptor.stdimpls.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link QuoteMonitorProductDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link QuoteMonitorProductRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see QuoteMonitorProductPK 
 * @@see QuoteMonitorProductRow 
 */
public class QuoteMonitorProductDao extends DataAccessObject {


  /** 
   * ����{@@link QuoteMonitorProductDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private QuoteMonitorProductRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link QuoteMonitorProductRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link QuoteMonitorProductDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link QuoteMonitorProductDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link QuoteMonitorProductRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QuoteMonitorProductRow )
                return new QuoteMonitorProductDao( (QuoteMonitorProductRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QuoteMonitorProductRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QuoteMonitorProductRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link QuoteMonitorProductRow}�I�u�W�F�N�g 
    */
    protected QuoteMonitorProductDao( QuoteMonitorProductRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link QuoteMonitorProductRow}�I�u�W�F�N�g���擾���܂��B
   */
    public QuoteMonitorProductRow getQuoteMonitorProductRow() {
        return row;
    }


  /** 
   * �w���{@@link QuoteMonitorProductRow}�I�u�W�F�N�g����{@@link QuoteMonitorProductDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link QuoteMonitorProductRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link QuoteMonitorProductDao}�擾�̂��߂Ɏw���{@@link QuoteMonitorProductRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link QuoteMonitorProductDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static QuoteMonitorProductDao forRow( QuoteMonitorProductRow row ) throws java.lang.IllegalArgumentException {
        return (QuoteMonitorProductDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QuoteMonitorProductRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link QuoteMonitorProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link QuoteMonitorProductPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link QuoteMonitorProductParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QuoteMonitorProductRow.TYPE );
    }


  /** 
   * {@@link QuoteMonitorProductRow}����ӂɓ��肷��{@@link QuoteMonitorProductPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link QuoteMonitorProductRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link QuoteMonitorProductParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link QuoteMonitorProductPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static QuoteMonitorProductPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link QuoteMonitorProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QuoteMonitorProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QuoteMonitorProductRow findRowByPk( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteMonitorProductPK pk = new QuoteMonitorProductPK( p_marketCode, p_productCode, p_productType );
        return findRowByPk( pk );
    }


  /** 
   * �w���QuoteMonitorProductPK�I�u�W�F�N�g����{@@link QuoteMonitorProductRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����QuoteMonitorProductPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QuoteMonitorProductRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QuoteMonitorProductRow findRowByPk( QuoteMonitorProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QuoteMonitorProductRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(QuoteMonitorProductRow)}���g�p���Ă��������B 
   */
    public static QuoteMonitorProductDao findDaoByPk( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteMonitorProductPK pk = new QuoteMonitorProductPK( p_marketCode, p_productCode, p_productType );
        QuoteMonitorProductRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(QuoteMonitorProductPK)}�����{@@link #forRow(QuoteMonitorProductRow)}���g�p���Ă��������B 
   */
    public static QuoteMonitorProductDao findDaoByPk( QuoteMonitorProductPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QuoteMonitorProductRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_marketCode, p_productCode, p_productType, and �ɂĎw��̒l�����ӂ�{@@link QuoteMonitorProductRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_productCode �����Ώۂł���p_productCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketCode, p_productCode, p_productType, and �̒l�ƈ�v����{@@link QuoteMonitorProductRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static QuoteMonitorProductRow findRowByMarketCodeProductCodeProductType( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QuoteMonitorProductRow.TYPE,
            "market_code=? and product_code=? and product_type=?",
            null,
            new Object[] { p_marketCode, p_productCode, p_productType } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QuoteMonitorProductRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QuoteMonitorProductDao.findRowsByMarketCodeProductCodeProductType(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByMarketCodeProductCodeProductType(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum)}�����{@@link #forRow(QuoteMonitorProductRow)}���g�p���Ă��������B 
   */
    public static QuoteMonitorProductDao findDaoByMarketCodeProductCodeProductType( String p_marketCode, String p_productCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeProductCodeProductType( p_marketCode, p_productCode, p_productType ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
