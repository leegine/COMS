head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostXbmfOrderNotifyDao.java;


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
 * {@@link HostXbmfOrderNotifyDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostXbmfOrderNotifyRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostXbmfOrderNotifyPK 
 * @@see HostXbmfOrderNotifyRow 
 */
public class HostXbmfOrderNotifyDao extends DataAccessObject {


  /** 
   * ����{@@link HostXbmfOrderNotifyDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostXbmfOrderNotifyRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostXbmfOrderNotifyRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostXbmfOrderNotifyDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostXbmfOrderNotifyDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostXbmfOrderNotifyRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostXbmfOrderNotifyRow )
                return new HostXbmfOrderNotifyDao( (HostXbmfOrderNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostXbmfOrderNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostXbmfOrderNotifyRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g 
    */
    protected HostXbmfOrderNotifyDao( HostXbmfOrderNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostXbmfOrderNotifyRow getHostXbmfOrderNotifyRow() {
        return row;
    }


  /** 
   * �w���{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g����{@@link HostXbmfOrderNotifyDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostXbmfOrderNotifyRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostXbmfOrderNotifyDao}�擾�̂��߂Ɏw���{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostXbmfOrderNotifyDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostXbmfOrderNotifyDao forRow( HostXbmfOrderNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostXbmfOrderNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostXbmfOrderNotifyRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostXbmfOrderNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostXbmfOrderNotifyPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostXbmfOrderNotifyParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostXbmfOrderNotifyRow.TYPE );
    }


  /** 
   * {@@link HostXbmfOrderNotifyRow}����ӂɓ��肷��{@@link HostXbmfOrderNotifyPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostXbmfOrderNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostXbmfOrderNotifyParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostXbmfOrderNotifyPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostXbmfOrderNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostXbmfOrderNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostXbmfOrderNotifyRow findRowByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderNotifyPK pk = new HostXbmfOrderNotifyPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostXbmfOrderNotifyPK�I�u�W�F�N�g����{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostXbmfOrderNotifyPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostXbmfOrderNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostXbmfOrderNotifyRow findRowByPk( HostXbmfOrderNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostXbmfOrderNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(HostXbmfOrderNotifyRow)}���g�p���Ă��������B 
   */
    public static HostXbmfOrderNotifyDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderNotifyPK pk = new HostXbmfOrderNotifyPK( p_institutionCode, p_branchCode, p_orderRequestNumber );
        HostXbmfOrderNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostXbmfOrderNotifyPK)}�����{@@link #forRow(HostXbmfOrderNotifyRow)}���g�p���Ă��������B 
   */
    public static HostXbmfOrderNotifyDao findDaoByPk( HostXbmfOrderNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostXbmfOrderNotifyRow row = findRowByPk( pk );
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
   * p_institutionCode, p_branchCode, p_orderRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_orderRequestNumber, and �̒l�ƈ�v����{@@link HostXbmfOrderNotifyRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static HostXbmfOrderNotifyRow findRowByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            HostXbmfOrderNotifyRow.TYPE,
            "institution_code=? and branch_code=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_orderRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (HostXbmfOrderNotifyRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query HostXbmfOrderNotifyDao.findRowsByInstitutionCodeBranchCodeOrderRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeOrderRequestNumber(String, String, String)}�����{@@link #forRow(HostXbmfOrderNotifyRow)}���g�p���Ă��������B 
   */
    public static HostXbmfOrderNotifyDao findDaoByInstitutionCodeBranchCodeOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_orderRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeOrderRequestNumber( p_institutionCode, p_branchCode, p_orderRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@