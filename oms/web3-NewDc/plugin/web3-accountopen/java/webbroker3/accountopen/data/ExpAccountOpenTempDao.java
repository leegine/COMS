head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.12.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	ExpAccountOpenTempDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.accountopen.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link ExpAccountOpenTempDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link ExpAccountOpenTempRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see ExpAccountOpenTempPK 
 * @@see ExpAccountOpenTempRow 
 */
public class ExpAccountOpenTempDao extends DataAccessObject {


  /** 
   * ����{@@link ExpAccountOpenTempDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private ExpAccountOpenTempRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link ExpAccountOpenTempRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link ExpAccountOpenTempDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link ExpAccountOpenTempDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link ExpAccountOpenTempRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof ExpAccountOpenTempRow )
                return new ExpAccountOpenTempDao( (ExpAccountOpenTempRow) row );
            throw new java.lang.IllegalArgumentException( "Not a ExpAccountOpenTempRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link ExpAccountOpenTempRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g 
    */
    protected ExpAccountOpenTempDao( ExpAccountOpenTempRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g���擾���܂��B
   */
    public ExpAccountOpenTempRow getExpAccountOpenTempRow() {
        return row;
    }


  /** 
   * �w���{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g����{@@link ExpAccountOpenTempDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link ExpAccountOpenTempRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link ExpAccountOpenTempDao}�擾�̂��߂Ɏw���{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link ExpAccountOpenTempDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static ExpAccountOpenTempDao forRow( ExpAccountOpenTempRow row ) throws java.lang.IllegalArgumentException {
        return (ExpAccountOpenTempDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link ExpAccountOpenTempRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link ExpAccountOpenTempRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link ExpAccountOpenTempPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link ExpAccountOpenTempParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( ExpAccountOpenTempRow.TYPE );
    }


  /** 
   * {@@link ExpAccountOpenTempRow}����ӂɓ��肷��{@@link ExpAccountOpenTempPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link ExpAccountOpenTempRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link ExpAccountOpenTempParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link ExpAccountOpenTempPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static ExpAccountOpenTempPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accOpenRequestNumber �����Ώۂł���p_accOpenRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExpAccountOpenTempRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExpAccountOpenTempRow findRowByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenTempPK pk = new ExpAccountOpenTempPK( p_institutionCode, p_accOpenRequestNumber );
        return findRowByPk( pk );
    }


  /** 
   * �w���ExpAccountOpenTempPK�I�u�W�F�N�g����{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����ExpAccountOpenTempPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link ExpAccountOpenTempRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static ExpAccountOpenTempRow findRowByPk( ExpAccountOpenTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ExpAccountOpenTempRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(ExpAccountOpenTempRow)}���g�p���Ă��������B 
   */
    public static ExpAccountOpenTempDao findDaoByPk( String p_institutionCode, String p_accOpenRequestNumber ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenTempPK pk = new ExpAccountOpenTempPK( p_institutionCode, p_accOpenRequestNumber );
        ExpAccountOpenTempRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(ExpAccountOpenTempPK)}�����{@@link #forRow(ExpAccountOpenTempRow)}���g�p���Ă��������B 
   */
    public static ExpAccountOpenTempDao findDaoByPk( ExpAccountOpenTempPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        ExpAccountOpenTempRow row = findRowByPk( pk );
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
   * p_institutionCode, p_accOpenRequestNumber, and �ɂĎw��̒l�����ӂ�{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_accOpenRequestNumber �����Ώۂł���p_accOpenRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_accOpenRequestNumber, and �̒l�ƈ�v����{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static ExpAccountOpenTempRow findRowByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            ExpAccountOpenTempRow.TYPE,
            "institution_code=? and acc_open_request_number=?",
            null,
            new Object[] { p_institutionCode, p_accOpenRequestNumber } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (ExpAccountOpenTempRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query ExpAccountOpenTempDao.findRowsByInstitutionCodeAccOpenRequestNumber(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeAccOpenRequestNumber(String, String)}�����{@@link #forRow(ExpAccountOpenTempRow)}���g�p���Ă��������B 
   */
    public static ExpAccountOpenTempDao findDaoByInstitutionCodeAccOpenRequestNumber( String p_institutionCode, String p_accOpenRequestNumber ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeAccOpenRequestNumber( p_institutionCode, p_accOpenRequestNumber ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_status, and �ɂĎw��̒l�Ɉ�v����{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_status, and �̒l�ƈ�v����{@@link ExpAccountOpenTempRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeStatus( String p_institutionCode, String p_status ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            ExpAccountOpenTempRow.TYPE,
            "institution_code=? and status=?",
            null,
            new Object[] { p_institutionCode, p_status } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeStatus(String, String)}�����{@@link #forRow(ExpAccountOpenTempRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeStatus( String p_institutionCode, String p_status ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeStatus( p_institutionCode, p_status ) );
    }

}
@
