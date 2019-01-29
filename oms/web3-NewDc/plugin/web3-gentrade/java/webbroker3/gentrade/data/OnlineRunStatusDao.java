head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.41.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	OnlineRunStatusDao.java;


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
 * {@@link OnlineRunStatusDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link OnlineRunStatusRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see OnlineRunStatusPK 
 * @@see OnlineRunStatusRow 
 */
public class OnlineRunStatusDao extends DataAccessObject {


  /** 
   * ����{@@link OnlineRunStatusDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private OnlineRunStatusRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link OnlineRunStatusRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link OnlineRunStatusDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link OnlineRunStatusDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link OnlineRunStatusRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof OnlineRunStatusRow )
                return new OnlineRunStatusDao( (OnlineRunStatusRow) row );
            throw new java.lang.IllegalArgumentException( "Not a OnlineRunStatusRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link OnlineRunStatusRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link OnlineRunStatusRow}�I�u�W�F�N�g 
    */
    protected OnlineRunStatusDao( OnlineRunStatusRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link OnlineRunStatusRow}�I�u�W�F�N�g���擾���܂��B
   */
    public OnlineRunStatusRow getOnlineRunStatusRow() {
        return row;
    }


  /** 
   * �w���{@@link OnlineRunStatusRow}�I�u�W�F�N�g����{@@link OnlineRunStatusDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link OnlineRunStatusRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link OnlineRunStatusDao}�擾�̂��߂Ɏw���{@@link OnlineRunStatusRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link OnlineRunStatusDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static OnlineRunStatusDao forRow( OnlineRunStatusRow row ) throws java.lang.IllegalArgumentException {
        return (OnlineRunStatusDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link OnlineRunStatusRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link OnlineRunStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link OnlineRunStatusPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link OnlineRunStatusParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( OnlineRunStatusRow.TYPE );
    }


  /** 
   * {@@link OnlineRunStatusRow}����ӂɓ��肷��{@@link OnlineRunStatusPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link OnlineRunStatusRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link OnlineRunStatusParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link OnlineRunStatusPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static OnlineRunStatusPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link OnlineRunStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * @@param p_onlineServiceDiv �����Ώۂł���p_onlineServiceDiv�t�B�[���h�̒l
   * @@param p_accountIdFrom �����Ώۂł���p_accountIdFrom�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OnlineRunStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OnlineRunStatusRow findRowByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        OnlineRunStatusPK pk = new OnlineRunStatusPK( p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom );
        return findRowByPk( pk );
    }


  /** 
   * �w���OnlineRunStatusPK�I�u�W�F�N�g����{@@link OnlineRunStatusRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����OnlineRunStatusPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link OnlineRunStatusRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static OnlineRunStatusRow findRowByPk( OnlineRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (OnlineRunStatusRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum,String,String,long)}�����{@@link #forRow(OnlineRunStatusRow)}���g�p���Ă��������B 
   */
    public static OnlineRunStatusDao findDaoByPk( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataFindException, DataQueryException, DataNetworkException {
        OnlineRunStatusPK pk = new OnlineRunStatusPK( p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom );
        OnlineRunStatusRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(OnlineRunStatusPK)}�����{@@link #forRow(OnlineRunStatusRow)}���g�p���Ă��������B 
   */
    public static OnlineRunStatusDao findDaoByPk( OnlineRunStatusPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        OnlineRunStatusRow row = findRowByPk( pk );
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
   * p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom, and �ɂĎw��̒l�����ӂ�{@@link OnlineRunStatusRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * @@param p_onlineServiceDiv �����Ώۂł���p_onlineServiceDiv�t�B�[���h�̒l
   * @@param p_accountIdFrom �����Ώۂł���p_accountIdFrom�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom, and �̒l�ƈ�v����{@@link OnlineRunStatusRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static OnlineRunStatusRow findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            OnlineRunStatusRow.TYPE,
            "institution_code=? and product_type=? and future_option_div=? and online_service_div=? and account_id_from=?",
            null,
            new Object[] { p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, new Long(p_accountIdFrom) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (OnlineRunStatusRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query OnlineRunStatusDao.findRowsByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String, String, long)}�����{@@link #forRow(OnlineRunStatusRow)}���g�p���Ă��������B 
   */
    public static OnlineRunStatusDao findDaoByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom( String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv, String p_onlineServiceDiv, long p_accountIdFrom ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom( p_institutionCode, p_productType, p_futureOptionDiv, p_onlineServiceDiv, p_accountIdFrom ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@