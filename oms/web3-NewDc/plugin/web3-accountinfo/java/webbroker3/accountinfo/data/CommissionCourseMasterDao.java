head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	CommissionCourseMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountinfo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link CommissionCourseMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CommissionCourseMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CommissionCourseMasterPK 
 * @@see CommissionCourseMasterRow 
 */
public class CommissionCourseMasterDao extends DataAccessObject {


  /** 
   * ����{@@link CommissionCourseMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CommissionCourseMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CommissionCourseMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CommissionCourseMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CommissionCourseMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CommissionCourseMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CommissionCourseMasterRow )
                return new CommissionCourseMasterDao( (CommissionCourseMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CommissionCourseMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CommissionCourseMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CommissionCourseMasterRow}�I�u�W�F�N�g 
    */
    protected CommissionCourseMasterDao( CommissionCourseMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CommissionCourseMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CommissionCourseMasterRow getCommissionCourseMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link CommissionCourseMasterRow}�I�u�W�F�N�g����{@@link CommissionCourseMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CommissionCourseMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CommissionCourseMasterDao}�擾�̂��߂Ɏw���{@@link CommissionCourseMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CommissionCourseMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CommissionCourseMasterDao forRow( CommissionCourseMasterRow row ) throws java.lang.IllegalArgumentException {
        return (CommissionCourseMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CommissionCourseMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CommissionCourseMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CommissionCourseMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CommissionCourseMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CommissionCourseMasterRow.TYPE );
    }


  /** 
   * {@@link CommissionCourseMasterRow}����ӂɓ��肷��{@@link CommissionCourseMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CommissionCourseMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CommissionCourseMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CommissionCourseMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CommissionCourseMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CommissionCourseMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_commissionCourseDiv �����Ώۂł���p_commissionCourseDiv�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommissionCourseMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommissionCourseMasterRow findRowByPk( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseMasterPK pk = new CommissionCourseMasterPK( p_institutionCode, p_commProductCode, p_commissionCourseDiv );
        return findRowByPk( pk );
    }


  /** 
   * �w���CommissionCourseMasterPK�I�u�W�F�N�g����{@@link CommissionCourseMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CommissionCourseMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CommissionCourseMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CommissionCourseMasterRow findRowByPk( CommissionCourseMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CommissionCourseMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(CommissionCourseMasterRow)}���g�p���Ă��������B 
   */
    public static CommissionCourseMasterDao findDaoByPk( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseMasterPK pk = new CommissionCourseMasterPK( p_institutionCode, p_commProductCode, p_commissionCourseDiv );
        CommissionCourseMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CommissionCourseMasterPK)}�����{@@link #forRow(CommissionCourseMasterRow)}���g�p���Ă��������B 
   */
    public static CommissionCourseMasterDao findDaoByPk( CommissionCourseMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CommissionCourseMasterRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_commissionCourseDiv, and �ɂĎw��̒l�����ӂ�{@@link CommissionCourseMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_commissionCourseDiv �����Ώۂł���p_commissionCourseDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_commProductCode, p_commissionCourseDiv, and �̒l�ƈ�v����{@@link CommissionCourseMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CommissionCourseMasterRow findRowByInstitutionCodeCommProductCodeCommissionCourseDiv( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CommissionCourseMasterRow.TYPE,
            "institution_code=? and comm_product_code=? and commission_course_div=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_commissionCourseDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CommissionCourseMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CommissionCourseMasterDao.findRowsByInstitutionCodeCommProductCodeCommissionCourseDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeCommProductCodeCommissionCourseDiv(String, String, String)}�����{@@link #forRow(CommissionCourseMasterRow)}���g�p���Ă��������B 
   */
    public static CommissionCourseMasterDao findDaoByInstitutionCodeCommProductCodeCommissionCourseDiv( String p_institutionCode, String p_commProductCode, String p_commissionCourseDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeCommissionCourseDiv( p_institutionCode, p_commProductCode, p_commissionCourseDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
