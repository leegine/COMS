head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.18.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	MainAccountAllDao.java;


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
 * {@@link MainAccountAllDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link MainAccountAllRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see MainAccountAllPK 
 * @@see MainAccountAllRow 
 */
public class MainAccountAllDao extends DataAccessObject {


  /** 
   * ����{@@link MainAccountAllDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private MainAccountAllRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link MainAccountAllRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link MainAccountAllDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link MainAccountAllDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link MainAccountAllRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof MainAccountAllRow )
                return new MainAccountAllDao( (MainAccountAllRow) row );
            throw new java.lang.IllegalArgumentException( "Not a MainAccountAllRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link MainAccountAllRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link MainAccountAllRow}�I�u�W�F�N�g 
    */
    protected MainAccountAllDao( MainAccountAllRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link MainAccountAllRow}�I�u�W�F�N�g���擾���܂��B
   */
    public MainAccountAllRow getMainAccountAllRow() {
        return row;
    }


  /** 
   * �w���{@@link MainAccountAllRow}�I�u�W�F�N�g����{@@link MainAccountAllDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link MainAccountAllRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link MainAccountAllDao}�擾�̂��߂Ɏw���{@@link MainAccountAllRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link MainAccountAllDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static MainAccountAllDao forRow( MainAccountAllRow row ) throws java.lang.IllegalArgumentException {
        return (MainAccountAllDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link MainAccountAllRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link MainAccountAllRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link MainAccountAllPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link MainAccountAllParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( MainAccountAllRow.TYPE );
    }


  /** 
   * {@@link MainAccountAllRow}����ӂɓ��肷��{@@link MainAccountAllPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link MainAccountAllRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link MainAccountAllParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link MainAccountAllPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static MainAccountAllPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link MainAccountAllRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_compCode �����Ώۂł���p_compCode�t�B�[���h�̒l
   * @@param p_brCode �����Ώۂł���p_brCode�t�B�[���h�̒l
   * @@param p_custCode �����Ώۂł���p_custCode�t�B�[���h�̒l
   * @@param p_custCodeCd �����Ώۂł���p_custCodeCd�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MainAccountAllRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MainAccountAllRow findRowByPk( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountAllPK pk = new MainAccountAllPK( p_compCode, p_brCode, p_custCode, p_custCodeCd );
        return findRowByPk( pk );
    }


  /** 
   * �w���MainAccountAllPK�I�u�W�F�N�g����{@@link MainAccountAllRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����MainAccountAllPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link MainAccountAllRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static MainAccountAllRow findRowByPk( MainAccountAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (MainAccountAllRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String)}�����{@@link #forRow(MainAccountAllRow)}���g�p���Ă��������B 
   */
    public static MainAccountAllDao findDaoByPk( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountAllPK pk = new MainAccountAllPK( p_compCode, p_brCode, p_custCode, p_custCodeCd );
        MainAccountAllRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(MainAccountAllPK)}�����{@@link #forRow(MainAccountAllRow)}���g�p���Ă��������B 
   */
    public static MainAccountAllDao findDaoByPk( MainAccountAllPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        MainAccountAllRow row = findRowByPk( pk );
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
   * p_compCode, p_brCode, p_custCode, p_custCodeCd, and �ɂĎw��̒l�����ӂ�{@@link MainAccountAllRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_compCode �����Ώۂł���p_compCode�t�B�[���h�̒l
   * @@param p_brCode �����Ώۂł���p_brCode�t�B�[���h�̒l
   * @@param p_custCode �����Ώۂł���p_custCode�t�B�[���h�̒l
   * @@param p_custCodeCd �����Ώۂł���p_custCodeCd�t�B�[���h�̒l
   * 
   * @@return �����w���p_compCode, p_brCode, p_custCode, p_custCodeCd, and �̒l�ƈ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static MainAccountAllRow findRowByCompCodeBrCodeCustCodeCustCodeCd( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "comp_code=? and br_code=? and cust_code=? and cust_code_cd=?",
            null,
            new Object[] { p_compCode, p_brCode, p_custCode, p_custCodeCd } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (MainAccountAllRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query MainAccountAllDao.findRowsByCompCodeBrCodeCustCodeCustCodeCd(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByCompCodeBrCodeCustCodeCustCodeCd(String, String, String, String)}�����{@@link #forRow(MainAccountAllRow)}���g�p���Ă��������B 
   */
    public static MainAccountAllDao findDaoByCompCodeBrCodeCustCodeCustCodeCd( String p_compCode, String p_brCode, String p_custCode, String p_custCodeCd ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCompCodeBrCodeCustCodeCustCodeCd( p_compCode, p_brCode, p_custCode, p_custCodeCd ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_eraBorn, p_bornDate, and �ɂĎw��̒l�Ɉ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_eraBorn �����Ώۂł���p_eraBorn�t�B�[���h�̒l
   * @@param p_bornDate �����Ώۂł���p_bornDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_eraBorn, p_bornDate, and �̒l�ƈ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByEraBornBornDate( String p_eraBorn, String p_bornDate ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "era_born=? and born_date=?",
            null,
            new Object[] { p_eraBorn, p_bornDate } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByEraBornBornDate(String, String)}�����{@@link #forRow(MainAccountAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByEraBornBornDate( String p_eraBorn, String p_bornDate ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByEraBornBornDate( p_eraBorn, p_bornDate ) );
    }


  /** 
   * p_telephone, and �ɂĎw��̒l�Ɉ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_telephone �����Ώۂł���p_telephone�t�B�[���h�̒l
   * 
   * @@return �����w���p_telephone, and �̒l�ƈ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByTelephone( String p_telephone ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "telephone=?",
            null,
            new Object[] { p_telephone } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByTelephone(String)}�����{@@link #forRow(MainAccountAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByTelephone( String p_telephone ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByTelephone( p_telephone ) );
    }


  /** 
   * p_contactAddressTelephone, and �ɂĎw��̒l�Ɉ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_contactAddressTelephone �����Ώۂł���p_contactAddressTelephone�t�B�[���h�̒l
   * 
   * @@return �����w���p_contactAddressTelephone, and �̒l�ƈ�v����{@@link MainAccountAllRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByContactAddressTelephone( String p_contactAddressTelephone ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            MainAccountAllRow.TYPE,
            "contact_address_telephone=?",
            null,
            new Object[] { p_contactAddressTelephone } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByContactAddressTelephone(String)}�����{@@link #forRow(MainAccountAllRow)}���g�p���Ă��������B 
   */
    public static List findDaosByContactAddressTelephone( String p_contactAddressTelephone ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByContactAddressTelephone( p_contactAddressTelephone ) );
    }

}
@
