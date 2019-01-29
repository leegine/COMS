head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqLimitPriceRangeMstDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link FeqLimitPriceRangeMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FeqLimitPriceRangeMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FeqLimitPriceRangeMstPK 
 * @@see FeqLimitPriceRangeMstRow 
 */
public class FeqLimitPriceRangeMstDao extends DataAccessObject {


  /** 
   * ����{@@link FeqLimitPriceRangeMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FeqLimitPriceRangeMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FeqLimitPriceRangeMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FeqLimitPriceRangeMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FeqLimitPriceRangeMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FeqLimitPriceRangeMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqLimitPriceRangeMstRow )
                return new FeqLimitPriceRangeMstDao( (FeqLimitPriceRangeMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqLimitPriceRangeMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqLimitPriceRangeMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g 
    */
    protected FeqLimitPriceRangeMstDao( FeqLimitPriceRangeMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FeqLimitPriceRangeMstRow getFeqLimitPriceRangeMstRow() {
        return row;
    }


  /** 
   * �w���{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g����{@@link FeqLimitPriceRangeMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FeqLimitPriceRangeMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FeqLimitPriceRangeMstDao}�擾�̂��߂Ɏw���{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FeqLimitPriceRangeMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FeqLimitPriceRangeMstDao forRow( FeqLimitPriceRangeMstRow row ) throws java.lang.IllegalArgumentException {
        return (FeqLimitPriceRangeMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqLimitPriceRangeMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FeqLimitPriceRangeMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FeqLimitPriceRangeMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FeqLimitPriceRangeMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqLimitPriceRangeMstRow.TYPE );
    }


  /** 
   * {@@link FeqLimitPriceRangeMstRow}����ӂɓ��肷��{@@link FeqLimitPriceRangeMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FeqLimitPriceRangeMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FeqLimitPriceRangeMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FeqLimitPriceRangeMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FeqLimitPriceRangeMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_lowPrice �����Ώۂł���p_lowPrice�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqLimitPriceRangeMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqLimitPriceRangeMstRow findRowByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLimitPriceRangeMstPK pk = new FeqLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * �w���FeqLimitPriceRangeMstPK�I�u�W�F�N�g����{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FeqLimitPriceRangeMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqLimitPriceRangeMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqLimitPriceRangeMstRow findRowByPk( FeqLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqLimitPriceRangeMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,double)}�����{@@link #forRow(FeqLimitPriceRangeMstRow)}���g�p���Ă��������B 
   */
    public static FeqLimitPriceRangeMstDao findDaoByPk( String p_marketCode, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLimitPriceRangeMstPK pk = new FeqLimitPriceRangeMstPK( p_marketCode, p_lowPrice );
        FeqLimitPriceRangeMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FeqLimitPriceRangeMstPK)}�����{@@link #forRow(FeqLimitPriceRangeMstRow)}���g�p���Ă��������B 
   */
    public static FeqLimitPriceRangeMstDao findDaoByPk( FeqLimitPriceRangeMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqLimitPriceRangeMstRow row = findRowByPk( pk );
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
   * p_marketCode, p_lowPrice, and �ɂĎw��̒l�����ӂ�{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_marketCode �����Ώۂł���p_marketCode�t�B�[���h�̒l
   * @@param p_lowPrice �����Ώۂł���p_lowPrice�t�B�[���h�̒l
   * 
   * @@return �����w���p_marketCode, p_lowPrice, and �̒l�ƈ�v����{@@link FeqLimitPriceRangeMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqLimitPriceRangeMstRow findRowByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqLimitPriceRangeMstRow.TYPE,
            "market_code=? and low_price=?",
            null,
            new Object[] { p_marketCode, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqLimitPriceRangeMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqLimitPriceRangeMstDao.findRowsByMarketCodeLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByMarketCodeLowPrice(String, double)}�����{@@link #forRow(FeqLimitPriceRangeMstRow)}���g�p���Ă��������B 
   */
    public static FeqLimitPriceRangeMstDao findDaoByMarketCodeLowPrice( String p_marketCode, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByMarketCodeLowPrice( p_marketCode, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
