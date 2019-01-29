head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.31.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpAssetHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TpAssetHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TpAssetHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TpAssetHistoryPK 
 * @@see TpAssetHistoryRow 
 */
public class TpAssetHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link TpAssetHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TpAssetHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TpAssetHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TpAssetHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TpAssetHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TpAssetHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpAssetHistoryRow )
                return new TpAssetHistoryDao( (TpAssetHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpAssetHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpAssetHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TpAssetHistoryRow}�I�u�W�F�N�g 
    */
    protected TpAssetHistoryDao( TpAssetHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TpAssetHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TpAssetHistoryRow getTpAssetHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link TpAssetHistoryRow}�I�u�W�F�N�g����{@@link TpAssetHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TpAssetHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TpAssetHistoryDao}�擾�̂��߂Ɏw���{@@link TpAssetHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TpAssetHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TpAssetHistoryDao forRow( TpAssetHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (TpAssetHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link TpAssetHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link TpAssetHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link TpAssetHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link TpAssetHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( TpAssetHistoryRow.TYPE );
    }


  /** 
   * {@@link TpAssetHistoryRow}����ӂɓ��肷��{@@link TpAssetHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link TpAssetHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link TpAssetHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link TpAssetHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static TpAssetHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new TpAssetHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link TpAssetHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_tpAssetHistoryId �����Ώۂł���p_tpAssetHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpAssetHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpAssetHistoryRow findRowByPk( long p_tpAssetHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpAssetHistoryPK pk = new TpAssetHistoryPK( p_tpAssetHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���TpAssetHistoryPK�I�u�W�F�N�g����{@@link TpAssetHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����TpAssetHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link TpAssetHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static TpAssetHistoryRow findRowByPk( TpAssetHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (TpAssetHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(TpAssetHistoryRow)}���g�p���Ă��������B 
   */
    public static TpAssetHistoryDao findDaoByPk( long p_tpAssetHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        TpAssetHistoryPK pk = new TpAssetHistoryPK( p_tpAssetHistoryId );
        TpAssetHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(TpAssetHistoryPK)}�����{@@link #forRow(TpAssetHistoryRow)}���g�p���Ă��������B 
   */
    public static TpAssetHistoryDao findDaoByPk( TpAssetHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        TpAssetHistoryRow row = findRowByPk( pk );
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
   * p_accountId, p_calcDate, and �ɂĎw��̒l�����ӂ�{@@link TpAssetHistoryRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_calcDate �����Ώۂł���p_calcDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_calcDate, and �̒l�ƈ�v����{@@link TpAssetHistoryRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static TpAssetHistoryRow findRowByAccountIdCalcDate( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            TpAssetHistoryRow.TYPE,
            "account_id=? and calc_date=?",
            null,
            new Object[] { new Long(p_accountId), p_calcDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (TpAssetHistoryRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query TpAssetHistoryDao.findRowsByAccountIdCalcDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdCalcDate(long, java.sql.Timestamp)}�����{@@link #forRow(TpAssetHistoryRow)}���g�p���Ă��������B 
   */
    public static TpAssetHistoryDao findDaoByAccountIdCalcDate( long p_accountId, java.sql.Timestamp p_calcDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdCalcDate( p_accountId, p_calcDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
