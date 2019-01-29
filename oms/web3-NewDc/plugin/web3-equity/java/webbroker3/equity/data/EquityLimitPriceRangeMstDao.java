head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityLimitPriceRangeMstDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link EquityLimitPriceRangeMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link EquityLimitPriceRangeMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see EquityLimitPriceRangeMstPK 
 * @@see EquityLimitPriceRangeMstRow 
 */
public class EquityLimitPriceRangeMstDao extends DataAccessObject {


  /** 
   * ����{@@link EquityLimitPriceRangeMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private EquityLimitPriceRangeMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link EquityLimitPriceRangeMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link EquityLimitPriceRangeMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link EquityLimitPriceRangeMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link EquityLimitPriceRangeMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityLimitPriceRangeMstRow )
                return new EquityLimitPriceRangeMstDao( (EquityLimitPriceRangeMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityLimitPriceRangeMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityLimitPriceRangeMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g 
    */
    protected EquityLimitPriceRangeMstDao( EquityLimitPriceRangeMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public EquityLimitPriceRangeMstRow getEquityLimitPriceRangeMstRow() {
        return row;
    }


  /** 
   * �w���{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g����{@@link EquityLimitPriceRangeMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link EquityLimitPriceRangeMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link EquityLimitPriceRangeMstDao}�擾�̂��߂Ɏw���{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link EquityLimitPriceRangeMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static EquityLimitPriceRangeMstDao forRow( EquityLimitPriceRangeMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityLimitPriceRangeMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityLimitPriceRangeMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link EquityLimitPriceRangeMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link EquityLimitPriceRangeMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link EquityLimitPriceRangeMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityLimitPriceRangeMstRow.TYPE );
    }


  /** 
   * {@@link EquityLimitPriceRangeMstRow}����ӂɓ��肷��{@@link EquityLimitPriceRangeMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link EquityLimitPriceRangeMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link EquityLimitPriceRangeMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link EquityLimitPriceRangeMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static EquityLimitPriceRangeMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_lowPrice �����Ώۂł���p_lowPrice�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EquityLimitPriceRangeMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EquityLimitPriceRangeMstRow findRowByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityLimitPriceRangeMstPK pk = new EquityLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * �w���EquityLimitPriceRangeMstPK�I�u�W�F�N�g����{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����EquityLimitPriceRangeMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EquityLimitPriceRangeMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EquityLimitPriceRangeMstRow findRowByPk( EquityLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityLimitPriceRangeMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,double)}�����{@@link #forRow(EquityLimitPriceRangeMstRow)}���g�p���Ă��������B 
   */
    public static EquityLimitPriceRangeMstDao findDaoByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityLimitPriceRangeMstPK pk = new EquityLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        EquityLimitPriceRangeMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(EquityLimitPriceRangeMstPK)}�����{@@link #forRow(EquityLimitPriceRangeMstRow)}���g�p���Ă��������B 
   */
    public static EquityLimitPriceRangeMstDao findDaoByPk( EquityLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityLimitPriceRangeMstRow row = findRowByPk( pk );
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
   * p_marketCode, p_lowPrice, and �ɂĎw��̒l�����ӂ�{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_lowPrice �����Ώۂł���p_lowPrice�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketCode, p_lowPrice, and �̒l�ƈ�v����{@@link EquityLimitPriceRangeMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EquityLimitPriceRangeMstRow findRowByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityLimitPriceRangeMstRow.TYPE,
            "market_code=? and low_price=?",
            null,
            new Object[] { p_marketCode, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityLimitPriceRangeMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityLimitPriceRangeMstDao.findRowsByMarketCodeLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByMarketCodeLowPrice(String, double)}�����{@@link #forRow(EquityLimitPriceRangeMstRow)}���g�p���Ă��������B 
   */
    public static EquityLimitPriceRangeMstDao findDaoByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeLowPrice( p_marketCode, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
