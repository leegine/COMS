head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DocForceLogoutRunStatusDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.docadmin.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link DocForceLogoutRunStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DocForceLogoutRunStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DocForceLogoutRunStatusPK 
 * @@see DocForceLogoutRunStatusRow 
 */
public class DocForceLogoutRunStatusDao extends DataAccessObject {


  /** 
   * ����{@@link DocForceLogoutRunStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DocForceLogoutRunStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DocForceLogoutRunStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DocForceLogoutRunStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DocForceLogoutRunStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DocForceLogoutRunStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DocForceLogoutRunStatusRow )
                return new DocForceLogoutRunStatusDao( (DocForceLogoutRunStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DocForceLogoutRunStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DocForceLogoutRunStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g 
    */
    protected DocForceLogoutRunStatusDao( DocForceLogoutRunStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DocForceLogoutRunStatusRow getDocForceLogoutRunStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g����{@@link DocForceLogoutRunStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DocForceLogoutRunStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DocForceLogoutRunStatusDao}�擾�̂��߂Ɏw���{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DocForceLogoutRunStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DocForceLogoutRunStatusDao forRow( DocForceLogoutRunStatusRow row ) throws java.lang.IllegalArgumentException {
        return (DocForceLogoutRunStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DocForceLogoutRunStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DocForceLogoutRunStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DocForceLogoutRunStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DocForceLogoutRunStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DocForceLogoutRunStatusRow.TYPE );
    }


  /** 
   * {@@link DocForceLogoutRunStatusRow}����ӂɓ��肷��{@@link DocForceLogoutRunStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DocForceLogoutRunStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DocForceLogoutRunStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DocForceLogoutRunStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DocForceLogoutRunStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accountIdFrom �����Ώۂł���p_accountIdFrom�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocForceLogoutRunStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocForceLogoutRunStatusRow findRowByPk( String p_institutionCode, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        DocForceLogoutRunStatusPK pk = new DocForceLogoutRunStatusPK( p_institutionCode, p_accountIdFrom );
        return findRowByPk( pk );
    }


  /** 
   * �w���DocForceLogoutRunStatusPK�I�u�W�F�N�g����{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DocForceLogoutRunStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DocForceLogoutRunStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DocForceLogoutRunStatusRow findRowByPk( DocForceLogoutRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DocForceLogoutRunStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,long)}�����{@@link #forRow(DocForceLogoutRunStatusRow)}���g�p���Ă��������B 
   */
    public static DocForceLogoutRunStatusDao findDaoByPk( String p_institutionCode, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        DocForceLogoutRunStatusPK pk = new DocForceLogoutRunStatusPK( p_institutionCode, p_accountIdFrom );
        DocForceLogoutRunStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DocForceLogoutRunStatusPK)}�����{@@link #forRow(DocForceLogoutRunStatusRow)}���g�p���Ă��������B 
   */
    public static DocForceLogoutRunStatusDao findDaoByPk( DocForceLogoutRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DocForceLogoutRunStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accountIdFrom, and �ɂĎw��̒l�����ӂ�{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accountIdFrom �����Ώۂł���p_accountIdFrom�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_accountIdFrom, and �̒l�ƈ�v����{@@link DocForceLogoutRunStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DocForceLogoutRunStatusRow findRowByInstitutionCodeAccountIdFrom( String p_institutionCode, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DocForceLogoutRunStatusRow.TYPE,
            "institution_code=? and account_id_from=?",
            null,
            new Object[] { p_institutionCode, new Long(p_accountIdFrom) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DocForceLogoutRunStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DocForceLogoutRunStatusDao.findRowsByInstitutionCodeAccountIdFrom(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeAccountIdFrom(String, long)}�����{@@link #forRow(DocForceLogoutRunStatusRow)}���g�p���Ă��������B 
   */
    public static DocForceLogoutRunStatusDao findDaoByInstitutionCodeAccountIdFrom( String p_institutionCode, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccountIdFrom( p_institutionCode, p_accountIdFrom ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
