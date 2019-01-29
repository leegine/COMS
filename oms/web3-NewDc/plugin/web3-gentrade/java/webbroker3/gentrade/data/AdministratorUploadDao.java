head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.16.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdministratorUploadDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AdministratorUploadDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AdministratorUploadRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AdministratorUploadPK 
 * @@see AdministratorUploadRow 
 */
public class AdministratorUploadDao extends DataAccessObject {


  /** 
   * ����{@@link AdministratorUploadDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AdministratorUploadRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AdministratorUploadRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AdministratorUploadDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AdministratorUploadDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AdministratorUploadRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdministratorUploadRow )
                return new AdministratorUploadDao( (AdministratorUploadRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdministratorUploadRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdministratorUploadRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AdministratorUploadRow}�I�u�W�F�N�g 
    */
    protected AdministratorUploadDao( AdministratorUploadRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AdministratorUploadRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AdministratorUploadRow getAdministratorUploadRow() {
        return row;
    }


  /** 
   * �w���{@@link AdministratorUploadRow}�I�u�W�F�N�g����{@@link AdministratorUploadDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AdministratorUploadRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AdministratorUploadDao}�擾�̂��߂Ɏw���{@@link AdministratorUploadRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AdministratorUploadDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AdministratorUploadDao forRow( AdministratorUploadRow row ) throws java.lang.IllegalArgumentException {
        return (AdministratorUploadDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdministratorUploadRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AdministratorUploadRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AdministratorUploadPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AdministratorUploadParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdministratorUploadRow.TYPE );
    }


  /** 
   * {@@link AdministratorUploadRow}����ӂɓ��肷��{@@link AdministratorUploadPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AdministratorUploadRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AdministratorUploadParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AdministratorUploadPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AdministratorUploadPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdministratorUploadPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AdministratorUploadRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_administratorUploadId �����Ώۂł���p_administratorUploadId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorUploadRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorUploadRow findRowByPk( long p_administratorUploadId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadPK pk = new AdministratorUploadPK( p_administratorUploadId );
        return findRowByPk( pk );
    }


  /** 
   * �w���AdministratorUploadPK�I�u�W�F�N�g����{@@link AdministratorUploadRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AdministratorUploadPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdministratorUploadRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdministratorUploadRow findRowByPk( AdministratorUploadPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdministratorUploadRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(AdministratorUploadRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadDao findDaoByPk( long p_administratorUploadId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadPK pk = new AdministratorUploadPK( p_administratorUploadId );
        AdministratorUploadRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AdministratorUploadPK)}�����{@@link #forRow(AdministratorUploadRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadDao findDaoByPk( AdministratorUploadPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdministratorUploadRow row = findRowByPk( pk );
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
   * p_administratorUploadId, and �ɂĎw��̒l�����ӂ�{@@link AdministratorUploadRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_administratorUploadId �����Ώۂł���p_administratorUploadId�t�B�[���h�̒l
   * 
   * @@return �����w���p_administratorUploadId, and �̒l�ƈ�v����{@@link AdministratorUploadRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorUploadRow findRowByAdministratorUploadId( long p_administratorUploadId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorUploadRow.TYPE,
            "administrator_upload_id=?",
            null,
            new Object[] { new Long(p_administratorUploadId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorUploadRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorUploadDao.findRowsByAdministratorUploadId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAdministratorUploadId(long)}�����{@@link #forRow(AdministratorUploadRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadDao findDaoByAdministratorUploadId( long p_administratorUploadId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAdministratorUploadId( p_administratorUploadId ) );
    }


  /** 
   * p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp, and �ɂĎw��̒l�����ӂ�{@@link AdministratorUploadRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_uploadFileId �����Ώۂł���p_uploadFileId�t�B�[���h�̒l
   * @@param p_uploadStartTimestamp �����Ώۂł���p_uploadStartTimestamp�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp, and �̒l�ƈ�v����{@@link AdministratorUploadRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static AdministratorUploadRow findRowByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_uploadFileId, java.sql.Timestamp p_uploadStartTimestamp ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            AdministratorUploadRow.TYPE,
            "institution_code=? and branch_code=? and product_type=? and upload_file_id=? and upload_start_timestamp=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (AdministratorUploadRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query AdministratorUploadDao.findRowsByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, java.sql.Timestamp)}�����{@@link #forRow(AdministratorUploadRow)}���g�p���Ă��������B 
   */
    public static AdministratorUploadDao findDaoByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp( String p_institutionCode, String p_branchCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_uploadFileId, java.sql.Timestamp p_uploadStartTimestamp ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeProductTypeUploadFileIdUploadStartTimestamp( p_institutionCode, p_branchCode, p_productType, p_uploadFileId, p_uploadStartTimestamp ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
