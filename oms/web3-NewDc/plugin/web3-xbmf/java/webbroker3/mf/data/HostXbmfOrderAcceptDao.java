head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostXbmfOrderAcceptDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.mf.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostXbmfOrderAcceptDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostXbmfOrderAcceptRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostXbmfOrderAcceptPK 
 * @@see HostXbmfOrderAcceptRow 
 */
public class HostXbmfOrderAcceptDao extends DataAccessObject {


  /** 
   * ����{@@link HostXbmfOrderAcceptDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostXbmfOrderAcceptRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostXbmfOrderAcceptRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostXbmfOrderAcceptDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostXbmfOrderAcceptDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostXbmfOrderAcceptRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostXbmfOrderAcceptRow )
                return new HostXbmfOrderAcceptDao( (HostXbmfOrderAcceptRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostXbmfOrderAcceptRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostXbmfOrderAcceptRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g 
    */
    protected HostXbmfOrderAcceptDao( HostXbmfOrderAcceptRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostXbmfOrderAcceptRow getHostXbmfOrderAcceptRow() {
        return row;
    }


  /** 
   * �w���{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g����{@@link HostXbmfOrderAcceptDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostXbmfOrderAcceptRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostXbmfOrderAcceptDao}�擾�̂��߂Ɏw���{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostXbmfOrderAcceptDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostXbmfOrderAcceptDao forRow( HostXbmfOrderAcceptRow row ) throws java.lang.IllegalArgumentException {
        return (HostXbmfOrderAcceptDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostXbmfOrderAcceptRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostXbmfOrderAcceptRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostXbmfOrderAcceptPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostXbmfOrderAcceptParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostXbmfOrderAcceptRow.TYPE );
    }


  /** 
   * {@@link HostXbmfOrderAcceptRow}����ӂɓ��肷��{@@link HostXbmfOrderAcceptPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostXbmfOrderAcceptRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostXbmfOrderAcceptParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostXbmfOrderAcceptPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostXbmfOrderAcceptPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostXbmfOrderAcceptRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostXbmfOrderAcceptRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderAcceptPK pk = new HostXbmfOrderAcceptPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostXbmfOrderAcceptPK�I�u�W�F�N�g����{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostXbmfOrderAcceptPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostXbmfOrderAcceptRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostXbmfOrderAcceptRow findRowByPk( HostXbmfOrderAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostXbmfOrderAcceptRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(HostXbmfOrderAcceptRow)}���g�p���Ă��������B 
   */
    public static HostXbmfOrderAcceptDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderAcceptPK pk = new HostXbmfOrderAcceptPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        HostXbmfOrderAcceptRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostXbmfOrderAcceptPK)}�����{@@link #forRow(HostXbmfOrderAcceptRow)}���g�p���Ă��������B 
   */
    public static HostXbmfOrderAcceptDao findDaoByPk( HostXbmfOrderAcceptPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderAcceptRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostXbmfOrderAcceptRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static HostXbmfOrderAcceptRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostXbmfOrderAcceptRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostXbmfOrderAcceptRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostXbmfOrderAcceptDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}�����{@@link #forRow(HostXbmfOrderAcceptRow)}���g�p���Ă��������B 
   */
    public static HostXbmfOrderAcceptDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
