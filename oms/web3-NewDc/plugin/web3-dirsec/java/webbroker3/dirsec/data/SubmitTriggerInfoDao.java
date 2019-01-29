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
filename	SubmitTriggerInfoDao.java;


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
 * {@@link SubmitTriggerInfoDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SubmitTriggerInfoRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SubmitTriggerInfoPK 
 * @@see SubmitTriggerInfoRow 
 */
public class SubmitTriggerInfoDao extends DataAccessObject {


  /** 
   * ����{@@link SubmitTriggerInfoDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SubmitTriggerInfoRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SubmitTriggerInfoRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SubmitTriggerInfoDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SubmitTriggerInfoDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SubmitTriggerInfoRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SubmitTriggerInfoRow )
                return new SubmitTriggerInfoDao( (SubmitTriggerInfoRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SubmitTriggerInfoRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SubmitTriggerInfoRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g 
    */
    protected SubmitTriggerInfoDao( SubmitTriggerInfoRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SubmitTriggerInfoRow getSubmitTriggerInfoRow() {
        return row;
    }


  /** 
   * �w���{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g����{@@link SubmitTriggerInfoDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SubmitTriggerInfoRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SubmitTriggerInfoDao}�擾�̂��߂Ɏw���{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SubmitTriggerInfoDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SubmitTriggerInfoDao forRow( SubmitTriggerInfoRow row ) throws java.lang.IllegalArgumentException {
        return (SubmitTriggerInfoDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SubmitTriggerInfoRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SubmitTriggerInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SubmitTriggerInfoPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SubmitTriggerInfoParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SubmitTriggerInfoRow.TYPE );
    }


  /** 
   * {@@link SubmitTriggerInfoRow}����ӂɓ��肷��{@@link SubmitTriggerInfoPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SubmitTriggerInfoRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SubmitTriggerInfoParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SubmitTriggerInfoPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SubmitTriggerInfoPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_jobId �����Ώۂł���p_jobId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SubmitTriggerInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SubmitTriggerInfoRow findRowByPk( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubmitTriggerInfoPK pk = new SubmitTriggerInfoPK( p_institutionCode, p_requestCode, p_jobId );
        return findRowByPk( pk );
    }


  /** 
   * �w���SubmitTriggerInfoPK�I�u�W�F�N�g����{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SubmitTriggerInfoPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SubmitTriggerInfoRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SubmitTriggerInfoRow findRowByPk( SubmitTriggerInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SubmitTriggerInfoRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(SubmitTriggerInfoRow)}���g�p���Ă��������B 
   */
    public static SubmitTriggerInfoDao findDaoByPk( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataFindException, DataQueryException, DataNetworkException {
        SubmitTriggerInfoPK pk = new SubmitTriggerInfoPK( p_institutionCode, p_requestCode, p_jobId );
        SubmitTriggerInfoRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SubmitTriggerInfoPK)}�����{@@link #forRow(SubmitTriggerInfoRow)}���g�p���Ă��������B 
   */
    public static SubmitTriggerInfoDao findDaoByPk( SubmitTriggerInfoPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SubmitTriggerInfoRow row = findRowByPk( pk );
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
   * p_institutionCode, p_requestCode, p_jobId, and �ɂĎw��̒l�����ӂ�{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_jobId �����Ώۂł���p_jobId�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_requestCode, p_jobId, and �̒l�ƈ�v����{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SubmitTriggerInfoRow findRowByInstitutionCodeRequestCodeJobId( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubmitTriggerInfoRow.TYPE,
            "institution_code=? and request_code=? and job_id=?",
            null,
            new Object[] { p_institutionCode, p_requestCode, p_jobId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubmitTriggerInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubmitTriggerInfoDao.findRowsByInstitutionCodeRequestCodeJobId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeRequestCodeJobId(String, String, String)}�����{@@link #forRow(SubmitTriggerInfoRow)}���g�p���Ă��������B 
   */
    public static SubmitTriggerInfoDao findDaoByInstitutionCodeRequestCodeJobId( String p_institutionCode, String p_requestCode, String p_jobId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeRequestCodeJobId( p_institutionCode, p_requestCode, p_jobId ) );
    }


  /** 
   * p_institutionCode, p_jobId, p_idNo, and �ɂĎw��̒l�����ӂ�{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_jobId �����Ώۂł���p_jobId�t�B�[���h�̒l
   * @@param p_idNo �����Ώۂł���p_idNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_jobId, p_idNo, and �̒l�ƈ�v����{@@link SubmitTriggerInfoRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SubmitTriggerInfoRow findRowByInstitutionCodeJobIdIdNo( String p_institutionCode, String p_jobId, String p_idNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SubmitTriggerInfoRow.TYPE,
            "institution_code=? and job_id=? and id_no=?",
            null,
            new Object[] { p_institutionCode, p_jobId, p_idNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SubmitTriggerInfoRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SubmitTriggerInfoDao.findRowsByInstitutionCodeJobIdIdNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeJobIdIdNo(String, String, String)}�����{@@link #forRow(SubmitTriggerInfoRow)}���g�p���Ă��������B 
   */
    public static SubmitTriggerInfoDao findDaoByInstitutionCodeJobIdIdNo( String p_institutionCode, String p_jobId, String p_idNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeJobIdIdNo( p_institutionCode, p_jobId, p_idNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
