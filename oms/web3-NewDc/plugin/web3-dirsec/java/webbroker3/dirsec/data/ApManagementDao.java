head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	ApManagementDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.dirsec.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link ApManagementDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ApManagementRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ApManagementPK 
 * @@see ApManagementRow 
 */
public class ApManagementDao extends DataAccessObject {


  /** 
   * ����{@@link ApManagementDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ApManagementRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ApManagementRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ApManagementDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ApManagementDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ApManagementRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ApManagementRow )
                return new ApManagementDao( (ApManagementRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ApManagementRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ApManagementRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ApManagementRow}�I�u�W�F�N�g 
    */
    protected ApManagementDao( ApManagementRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ApManagementRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ApManagementRow getApManagementRow() {
        return row;
    }


  /** 
   * �w���{@@link ApManagementRow}�I�u�W�F�N�g����{@@link ApManagementDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ApManagementRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ApManagementDao}�擾�̂��߂Ɏw���{@@link ApManagementRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ApManagementDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ApManagementDao forRow( ApManagementRow row ) throws java.lang.IllegalArgumentException {
        return (ApManagementDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ApManagementRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ApManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ApManagementPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ApManagementParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ApManagementRow.TYPE );
    }


  /** 
   * {@@link ApManagementRow}����ӂɓ��肷��{@@link ApManagementPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ApManagementRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ApManagementParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ApManagementPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ApManagementPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ApManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_ptype �����Ώۂł���p_ptype�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ApManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ApManagementRow findRowByPk( String p_ptype, String p_requestCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ApManagementPK pk = new ApManagementPK( p_ptype, p_requestCode );
        return findRowByPk( pk );
    }


  /** 
   * �w���ApManagementPK�I�u�W�F�N�g����{@@link ApManagementRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ApManagementPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ApManagementRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ApManagementRow findRowByPk( ApManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ApManagementRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(ApManagementRow)}���g�p���Ă��������B 
   */
    public static ApManagementDao findDaoByPk( String p_ptype, String p_requestCode ) throws DataFindException, DataQueryException, DataNetworkException {
        ApManagementPK pk = new ApManagementPK( p_ptype, p_requestCode );
        ApManagementRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ApManagementPK)}�����{@@link #forRow(ApManagementRow)}���g�p���Ă��������B 
   */
    public static ApManagementDao findDaoByPk( ApManagementPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ApManagementRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_ptype, p_requestCode, and �ɂĎw��̒l�Ɉ�v����{@@link ApManagementRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_ptype �����Ώۂł���p_ptype�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_ptype, p_requestCode, and �̒l�ƈ�v����{@@link ApManagementRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByPtypeRequestCode( String p_ptype, String p_requestCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ApManagementRow.TYPE,
            "ptype=? and request_code=?",
            null,
            new Object[] { p_ptype, p_requestCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByPtypeRequestCode(String, String)}�����{@@link #forRow(ApManagementRow)}���g�p���Ă��������B 
   */
    public static List findDaosByPtypeRequestCode( String p_ptype, String p_requestCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByPtypeRequestCode( p_ptype, p_requestCode ) );
    }

}
@
