head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SoapConnectPrefMsgDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link SoapConnectPrefMsgDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SoapConnectPrefMsgRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SoapConnectPrefMsgPK 
 * @@see SoapConnectPrefMsgRow 
 */
public class SoapConnectPrefMsgDao extends DataAccessObject {


  /** 
   * ����{@@link SoapConnectPrefMsgDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SoapConnectPrefMsgRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SoapConnectPrefMsgRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SoapConnectPrefMsgDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SoapConnectPrefMsgDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SoapConnectPrefMsgRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SoapConnectPrefMsgRow )
                return new SoapConnectPrefMsgDao( (SoapConnectPrefMsgRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SoapConnectPrefMsgRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SoapConnectPrefMsgRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g 
    */
    protected SoapConnectPrefMsgDao( SoapConnectPrefMsgRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SoapConnectPrefMsgRow getSoapConnectPrefMsgRow() {
        return row;
    }


  /** 
   * �w���{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g����{@@link SoapConnectPrefMsgDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SoapConnectPrefMsgRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SoapConnectPrefMsgDao}�擾�̂��߂Ɏw���{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SoapConnectPrefMsgDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SoapConnectPrefMsgDao forRow( SoapConnectPrefMsgRow row ) throws java.lang.IllegalArgumentException {
        return (SoapConnectPrefMsgDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SoapConnectPrefMsgRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SoapConnectPrefMsgRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SoapConnectPrefMsgPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SoapConnectPrefMsgParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SoapConnectPrefMsgRow.TYPE );
    }


  /** 
   * {@@link SoapConnectPrefMsgRow}����ӂɓ��肷��{@@link SoapConnectPrefMsgPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SoapConnectPrefMsgRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SoapConnectPrefMsgParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SoapConnectPrefMsgPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SoapConnectPrefMsgPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_connectDiv �����Ώۂł���p_connectDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SoapConnectPrefMsgRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SoapConnectPrefMsgRow findRowByPk( long p_branchId, String p_connectDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefMsgPK pk = new SoapConnectPrefMsgPK( p_branchId, p_connectDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���SoapConnectPrefMsgPK�I�u�W�F�N�g����{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SoapConnectPrefMsgPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SoapConnectPrefMsgRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SoapConnectPrefMsgRow findRowByPk( SoapConnectPrefMsgPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SoapConnectPrefMsgRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(SoapConnectPrefMsgRow)}���g�p���Ă��������B 
   */
    public static SoapConnectPrefMsgDao findDaoByPk( long p_branchId, String p_connectDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefMsgPK pk = new SoapConnectPrefMsgPK( p_branchId, p_connectDiv );
        SoapConnectPrefMsgRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SoapConnectPrefMsgPK)}�����{@@link #forRow(SoapConnectPrefMsgRow)}���g�p���Ă��������B 
   */
    public static SoapConnectPrefMsgDao findDaoByPk( SoapConnectPrefMsgPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SoapConnectPrefMsgRow row = findRowByPk( pk );
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
   * p_branchId, p_connectDiv, and �ɂĎw��̒l�����ӂ�{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_branchId �����Ώۂł���p_branchId�t�B�[���h�̒l
   * @@param p_connectDiv �����Ώۂł���p_connectDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_branchId, p_connectDiv, and �̒l�ƈ�v����{@@link SoapConnectPrefMsgRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SoapConnectPrefMsgRow findRowByBranchIdConnectDiv( long p_branchId, String p_connectDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SoapConnectPrefMsgRow.TYPE,
            "branch_id=? and connect_div=?",
            null,
            new Object[] { new Long(p_branchId), p_connectDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SoapConnectPrefMsgRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SoapConnectPrefMsgDao.findRowsByBranchIdConnectDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByBranchIdConnectDiv(long, String)}�����{@@link #forRow(SoapConnectPrefMsgRow)}���g�p���Ă��������B 
   */
    public static SoapConnectPrefMsgDao findDaoByBranchIdConnectDiv( long p_branchId, String p_connectDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByBranchIdConnectDiv( p_branchId, p_connectDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
