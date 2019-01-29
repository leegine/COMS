head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.37.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	FxTransferMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link FxTransferMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FxTransferMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see FxTransferMasterPK 
 * @@see FxTransferMasterRow 
 */
public class FxTransferMasterDao extends DataAccessObject {


  /** 
   * ����{@@link FxTransferMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FxTransferMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FxTransferMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FxTransferMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FxTransferMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FxTransferMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FxTransferMasterRow )
                return new FxTransferMasterDao( (FxTransferMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FxTransferMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FxTransferMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FxTransferMasterRow}�I�u�W�F�N�g 
    */
    protected FxTransferMasterDao( FxTransferMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FxTransferMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FxTransferMasterRow getFxTransferMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link FxTransferMasterRow}�I�u�W�F�N�g����{@@link FxTransferMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FxTransferMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FxTransferMasterDao}�擾�̂��߂Ɏw���{@@link FxTransferMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FxTransferMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FxTransferMasterDao forRow( FxTransferMasterRow row ) throws java.lang.IllegalArgumentException {
        return (FxTransferMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FxTransferMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FxTransferMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FxTransferMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FxTransferMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FxTransferMasterRow.TYPE );
    }


  /** 
   * {@@link FxTransferMasterRow}����ӂɓ��肷��{@@link FxTransferMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FxTransferMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FxTransferMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FxTransferMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FxTransferMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FxTransferMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_fxSystemId �����Ώۂł���p_fxSystemId�t�B�[���h�̒l
   * @@param p_transferDiv �����Ώۂł���p_transferDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxTransferMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxTransferMasterRow findRowByPk( long p_fxSystemId, String p_transferDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxTransferMasterPK pk = new FxTransferMasterPK( p_fxSystemId, p_transferDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���FxTransferMasterPK�I�u�W�F�N�g����{@@link FxTransferMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FxTransferMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FxTransferMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FxTransferMasterRow findRowByPk( FxTransferMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FxTransferMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(FxTransferMasterRow)}���g�p���Ă��������B 
   */
    public static FxTransferMasterDao findDaoByPk( long p_fxSystemId, String p_transferDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        FxTransferMasterPK pk = new FxTransferMasterPK( p_fxSystemId, p_transferDiv );
        FxTransferMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FxTransferMasterPK)}�����{@@link #forRow(FxTransferMasterRow)}���g�p���Ă��������B 
   */
    public static FxTransferMasterDao findDaoByPk( FxTransferMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FxTransferMasterRow row = findRowByPk( pk );
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
   * p_fxSystemId, p_transferDiv, and �ɂĎw��̒l�����ӂ�{@@link FxTransferMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_fxSystemId �����Ώۂł���p_fxSystemId�t�B�[���h�̒l
   * @@param p_transferDiv �����Ώۂł���p_transferDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_fxSystemId, p_transferDiv, and �̒l�ƈ�v����{@@link FxTransferMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FxTransferMasterRow findRowByFxSystemIdTransferDiv( long p_fxSystemId, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FxTransferMasterRow.TYPE,
            "fx_system_id=? and transfer_div=?",
            null,
            new Object[] { new Long(p_fxSystemId), p_transferDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FxTransferMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FxTransferMasterDao.findRowsByFxSystemIdTransferDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByFxSystemIdTransferDiv(long, String)}�����{@@link #forRow(FxTransferMasterRow)}���g�p���Ă��������B 
   */
    public static FxTransferMasterDao findDaoByFxSystemIdTransferDiv( long p_fxSystemId, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByFxSystemIdTransferDiv( p_fxSystemId, p_transferDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
