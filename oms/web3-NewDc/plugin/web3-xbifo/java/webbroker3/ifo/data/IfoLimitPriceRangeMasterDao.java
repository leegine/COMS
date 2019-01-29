head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	IfoLimitPriceRangeMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link IfoLimitPriceRangeMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link IfoLimitPriceRangeMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see IfoLimitPriceRangeMasterPK 
 * @@see IfoLimitPriceRangeMasterRow 
 */
public class IfoLimitPriceRangeMasterDao extends DataAccessObject {


  /** 
   * ����{@@link IfoLimitPriceRangeMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private IfoLimitPriceRangeMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link IfoLimitPriceRangeMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link IfoLimitPriceRangeMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link IfoLimitPriceRangeMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link IfoLimitPriceRangeMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof IfoLimitPriceRangeMasterRow )
                return new IfoLimitPriceRangeMasterDao( (IfoLimitPriceRangeMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a IfoLimitPriceRangeMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link IfoLimitPriceRangeMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g 
    */
    protected IfoLimitPriceRangeMasterDao( IfoLimitPriceRangeMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public IfoLimitPriceRangeMasterRow getIfoLimitPriceRangeMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g����{@@link IfoLimitPriceRangeMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link IfoLimitPriceRangeMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link IfoLimitPriceRangeMasterDao}�擾�̂��߂Ɏw���{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link IfoLimitPriceRangeMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static IfoLimitPriceRangeMasterDao forRow( IfoLimitPriceRangeMasterRow row ) throws java.lang.IllegalArgumentException {
        return (IfoLimitPriceRangeMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link IfoLimitPriceRangeMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link IfoLimitPriceRangeMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link IfoLimitPriceRangeMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link IfoLimitPriceRangeMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( IfoLimitPriceRangeMasterRow.TYPE );
    }


  /** 
   * {@@link IfoLimitPriceRangeMasterRow}����ӂɓ��肷��{@@link IfoLimitPriceRangeMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link IfoLimitPriceRangeMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link IfoLimitPriceRangeMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link IfoLimitPriceRangeMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static IfoLimitPriceRangeMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_targetProductCode �����Ώۂł���p_targetProductCode�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * @@param p_lowPrice �����Ώۂł���p_lowPrice�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoLimitPriceRangeMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoLimitPriceRangeMasterRow findRowByPk( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLimitPriceRangeMasterPK pk = new IfoLimitPriceRangeMasterPK( p_targetProductCode, p_futureOptionDiv, p_lowPrice );
        return findRowByPk( pk );
    }


  /** 
   * �w���IfoLimitPriceRangeMasterPK�I�u�W�F�N�g����{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����IfoLimitPriceRangeMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link IfoLimitPriceRangeMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static IfoLimitPriceRangeMasterRow findRowByPk( IfoLimitPriceRangeMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (IfoLimitPriceRangeMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,double)}�����{@@link #forRow(IfoLimitPriceRangeMasterRow)}���g�p���Ă��������B 
   */
    public static IfoLimitPriceRangeMasterDao findDaoByPk( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLimitPriceRangeMasterPK pk = new IfoLimitPriceRangeMasterPK( p_targetProductCode, p_futureOptionDiv, p_lowPrice );
        IfoLimitPriceRangeMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(IfoLimitPriceRangeMasterPK)}�����{@@link #forRow(IfoLimitPriceRangeMasterRow)}���g�p���Ă��������B 
   */
    public static IfoLimitPriceRangeMasterDao findDaoByPk( IfoLimitPriceRangeMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        IfoLimitPriceRangeMasterRow row = findRowByPk( pk );
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
   * p_targetProductCode, p_futureOptionDiv, p_lowPrice, and �ɂĎw��̒l�����ӂ�{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_targetProductCode �����Ώۂł���p_targetProductCode�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * @@param p_lowPrice �����Ώۂł���p_lowPrice�t�B�[���h�̒l
   * 
   * @@return �����w���p_targetProductCode, p_futureOptionDiv, p_lowPrice, and �̒l�ƈ�v����{@@link IfoLimitPriceRangeMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static IfoLimitPriceRangeMasterRow findRowByTargetProductCodeFutureOptionDivLowPrice( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            IfoLimitPriceRangeMasterRow.TYPE,
            "target_product_code=? and future_option_div=? and low_price=?",
            null,
            new Object[] { p_targetProductCode, p_futureOptionDiv, new Double(p_lowPrice) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (IfoLimitPriceRangeMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query IfoLimitPriceRangeMasterDao.findRowsByTargetProductCodeFutureOptionDivLowPrice(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByTargetProductCodeFutureOptionDivLowPrice(String, String, double)}�����{@@link #forRow(IfoLimitPriceRangeMasterRow)}���g�p���Ă��������B 
   */
    public static IfoLimitPriceRangeMasterDao findDaoByTargetProductCodeFutureOptionDivLowPrice( String p_targetProductCode, String p_futureOptionDiv, double p_lowPrice ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByTargetProductCodeFutureOptionDivLowPrice( p_targetProductCode, p_futureOptionDiv, p_lowPrice ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
