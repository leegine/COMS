head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.32.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	EquityCommCondMstDao.java;


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
 * {@@link EquityCommCondMstDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link EquityCommCondMstRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see EquityCommCondMstPK 
 * @@see EquityCommCondMstRow 
 */
public class EquityCommCondMstDao extends DataAccessObject {


  /** 
   * ����{@@link EquityCommCondMstDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private EquityCommCondMstRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link EquityCommCondMstRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link EquityCommCondMstDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link EquityCommCondMstDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link EquityCommCondMstRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof EquityCommCondMstRow )
                return new EquityCommCondMstDao( (EquityCommCondMstRow) row );
            throw new java.lang.IllegalArgumentException( "Not a EquityCommCondMstRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link EquityCommCondMstRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link EquityCommCondMstRow}�I�u�W�F�N�g 
    */
    protected EquityCommCondMstDao( EquityCommCondMstRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link EquityCommCondMstRow}�I�u�W�F�N�g���擾���܂��B
   */
    public EquityCommCondMstRow getEquityCommCondMstRow() {
        return row;
    }


  /** 
   * �w���{@@link EquityCommCondMstRow}�I�u�W�F�N�g����{@@link EquityCommCondMstDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link EquityCommCondMstRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link EquityCommCondMstDao}�擾�̂��߂Ɏw���{@@link EquityCommCondMstRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link EquityCommCondMstDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static EquityCommCondMstDao forRow( EquityCommCondMstRow row ) throws java.lang.IllegalArgumentException {
        return (EquityCommCondMstDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link EquityCommCondMstRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link EquityCommCondMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link EquityCommCondMstPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link EquityCommCondMstParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( EquityCommCondMstRow.TYPE );
    }


  /** 
   * {@@link EquityCommCondMstRow}����ӂɓ��肷��{@@link EquityCommCondMstPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link EquityCommCondMstRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link EquityCommCondMstParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link EquityCommCondMstPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static EquityCommCondMstPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link EquityCommCondMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_regNo �����Ώۂł���p_regNo�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EquityCommCondMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EquityCommCondMstRow findRowByPk( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondMstPK pk = new EquityCommCondMstPK( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���EquityCommCondMstPK�I�u�W�F�N�g����{@@link EquityCommCondMstRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����EquityCommCondMstPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link EquityCommCondMstRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static EquityCommCondMstRow findRowByPk( EquityCommCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (EquityCommCondMstRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,java.sql.Timestamp)}�����{@@link #forRow(EquityCommCondMstRow)}���g�p���Ă��������B 
   */
    public static EquityCommCondMstDao findDaoByPk( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondMstPK pk = new EquityCommCondMstPK( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate );
        EquityCommCondMstRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(EquityCommCondMstPK)}�����{@@link #forRow(EquityCommCondMstRow)}���g�p���Ă��������B 
   */
    public static EquityCommCondMstDao findDaoByPk( EquityCommCondMstPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        EquityCommCondMstRow row = findRowByPk( pk );
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
   * p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, and �ɂĎw��̒l�����ӂ�{@@link EquityCommCondMstRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_commProductCode �����Ώۂł���p_commProductCode�t�B�[���h�̒l
   * @@param p_regNo �����Ώۂł���p_regNo�t�B�[���h�̒l
   * @@param p_appliStartDate �����Ώۂł���p_appliStartDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate, and �̒l�ƈ�v����{@@link EquityCommCondMstRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static EquityCommCondMstRow findRowByInstitutionCodeCommProductCodeRegNoAppliStartDate( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            EquityCommCondMstRow.TYPE,
            "institution_code=? and comm_product_code=? and reg_no=? and appli_start_date=?",
            null,
            new Object[] { p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (EquityCommCondMstRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query EquityCommCondMstDao.findRowsByInstitutionCodeCommProductCodeRegNoAppliStartDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeCommProductCodeRegNoAppliStartDate(String, String, String, java.sql.Timestamp)}�����{@@link #forRow(EquityCommCondMstRow)}���g�p���Ă��������B 
   */
    public static EquityCommCondMstDao findDaoByInstitutionCodeCommProductCodeRegNoAppliStartDate( String p_institutionCode, String p_commProductCode, String p_regNo, java.sql.Timestamp p_appliStartDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeCommProductCodeRegNoAppliStartDate( p_institutionCode, p_commProductCode, p_regNo, p_appliStartDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
