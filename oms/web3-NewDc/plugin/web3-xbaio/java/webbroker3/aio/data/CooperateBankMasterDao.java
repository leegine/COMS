head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.40.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	CooperateBankMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link CooperateBankMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CooperateBankMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CooperateBankMasterPK 
 * @@see CooperateBankMasterRow 
 */
public class CooperateBankMasterDao extends DataAccessObject {


  /** 
   * ����{@@link CooperateBankMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CooperateBankMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CooperateBankMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CooperateBankMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CooperateBankMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CooperateBankMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CooperateBankMasterRow )
                return new CooperateBankMasterDao( (CooperateBankMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CooperateBankMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CooperateBankMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CooperateBankMasterRow}�I�u�W�F�N�g 
    */
    protected CooperateBankMasterDao( CooperateBankMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CooperateBankMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CooperateBankMasterRow getCooperateBankMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link CooperateBankMasterRow}�I�u�W�F�N�g����{@@link CooperateBankMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CooperateBankMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CooperateBankMasterDao}�擾�̂��߂Ɏw���{@@link CooperateBankMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CooperateBankMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CooperateBankMasterDao forRow( CooperateBankMasterRow row ) throws java.lang.IllegalArgumentException {
        return (CooperateBankMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CooperateBankMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CooperateBankMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CooperateBankMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CooperateBankMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CooperateBankMasterRow.TYPE );
    }


  /** 
   * {@@link CooperateBankMasterRow}����ӂɓ��肷��{@@link CooperateBankMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CooperateBankMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CooperateBankMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CooperateBankMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CooperateBankMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CooperateBankMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CooperateBankMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CooperateBankMasterRow findRowByPk( String p_paySchemeId ) throws DataFindException, DataQueryException, DataNetworkException {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( p_paySchemeId );
        return findRowByPk( pk );
    }


  /** 
   * �w���CooperateBankMasterPK�I�u�W�F�N�g����{@@link CooperateBankMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CooperateBankMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CooperateBankMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CooperateBankMasterRow findRowByPk( CooperateBankMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CooperateBankMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(CooperateBankMasterRow)}���g�p���Ă��������B 
   */
    public static CooperateBankMasterDao findDaoByPk( String p_paySchemeId ) throws DataFindException, DataQueryException, DataNetworkException {
        CooperateBankMasterPK pk = new CooperateBankMasterPK( p_paySchemeId );
        CooperateBankMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CooperateBankMasterPK)}�����{@@link #forRow(CooperateBankMasterRow)}���g�p���Ă��������B 
   */
    public static CooperateBankMasterDao findDaoByPk( CooperateBankMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CooperateBankMasterRow row = findRowByPk( pk );
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
    // Fetch Rows having foreign keys on this object
    //
    //===========================================================================


  /** 
   * ����{@@link CooperateBankMasterDao}�Ɋ֘A����{@@link CooperateBankMasterRow}�̊O���L�[������{@@link CompBankConditionRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * 
   * @@return ����{@@link CooperateBankMasterDao}�Ɋ֘A����{@@link CooperateBankMasterRow}�̊O���L�[������{@@link CompBankConditionRow}�I�u�W�F�N�g��{@@link List}
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public List fetchCompBankConditionRowsByPaySchemeId() throws DataNetworkException, DataQueryException  {
        return CompBankConditionDao.findRowsByPaySchemeId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchCompBankConditionRowsByPaySchemeId()}�����{@@link #forRow(CooperateBankMasterRow)}���g�p���Ă��������B 
   */
    public List fetchCompBankConditionDaosByPaySchemeId() throws DataNetworkException, DataQueryException  {
        return CompBankConditionDao.findDaosByPaySchemeId( row );
    }


  /** 
   * @@deprecated �����{@@link #fetchCompBankConditionRowsByPaySchemeId()}�����{@@link #forRow(CooperateBankMasterRow)}���g�p���Ă��������B 
   */
    public List fetchCompBankConditionDaosPaySchemeId() throws DataNetworkException, DataQueryException  {
        return fetchCompBankConditionDaosByPaySchemeId();
    }


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_paySchemeId, and �ɂĎw��̒l�����ӂ�{@@link CooperateBankMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_paySchemeId �����Ώۂł���p_paySchemeId�t�B�[���h�̒l
   * 
   * @@return �����w���p_paySchemeId, and �̒l�ƈ�v����{@@link CooperateBankMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CooperateBankMasterRow findRowByPaySchemeId( String p_paySchemeId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CooperateBankMasterRow.TYPE,
            "pay_scheme_id=?",
            null,
            new Object[] { p_paySchemeId } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CooperateBankMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CooperateBankMasterDao.findRowsByPaySchemeId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByPaySchemeId(String)}�����{@@link #forRow(CooperateBankMasterRow)}���g�p���Ă��������B 
   */
    public static CooperateBankMasterDao findDaoByPaySchemeId( String p_paySchemeId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByPaySchemeId( p_paySchemeId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
