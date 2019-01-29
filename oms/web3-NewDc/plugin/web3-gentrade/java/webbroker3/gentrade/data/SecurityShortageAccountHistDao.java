head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.42.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	SecurityShortageAccountHistDao.java;


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
 * {@@link SecurityShortageAccountHistDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link SecurityShortageAccountHistRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see SecurityShortageAccountHistPK 
 * @@see SecurityShortageAccountHistRow 
 */
public class SecurityShortageAccountHistDao extends DataAccessObject {


  /** 
   * ����{@@link SecurityShortageAccountHistDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private SecurityShortageAccountHistRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link SecurityShortageAccountHistRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link SecurityShortageAccountHistDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link SecurityShortageAccountHistDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link SecurityShortageAccountHistRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof SecurityShortageAccountHistRow )
                return new SecurityShortageAccountHistDao( (SecurityShortageAccountHistRow) row );
            throw new java.lang.IllegalArgumentException( "Not a SecurityShortageAccountHistRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link SecurityShortageAccountHistRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g 
    */
    protected SecurityShortageAccountHistDao( SecurityShortageAccountHistRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g���擾���܂��B
   */
    public SecurityShortageAccountHistRow getSecurityShortageAccountHistRow() {
        return row;
    }


  /** 
   * �w���{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g����{@@link SecurityShortageAccountHistDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link SecurityShortageAccountHistRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link SecurityShortageAccountHistDao}�擾�̂��߂Ɏw���{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link SecurityShortageAccountHistDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static SecurityShortageAccountHistDao forRow( SecurityShortageAccountHistRow row ) throws java.lang.IllegalArgumentException {
        return (SecurityShortageAccountHistDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link SecurityShortageAccountHistRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link SecurityShortageAccountHistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link SecurityShortageAccountHistPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link SecurityShortageAccountHistParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( SecurityShortageAccountHistRow.TYPE );
    }


  /** 
   * {@@link SecurityShortageAccountHistRow}����ӂɓ��肷��{@@link SecurityShortageAccountHistPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link SecurityShortageAccountHistRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link SecurityShortageAccountHistParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link SecurityShortageAccountHistPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static SecurityShortageAccountHistPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_procDate �����Ώۂł���p_procDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SecurityShortageAccountHistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SecurityShortageAccountHistRow findRowByPk( long p_accountId, String p_procDate ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountHistPK pk = new SecurityShortageAccountHistPK( p_accountId, p_procDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���SecurityShortageAccountHistPK�I�u�W�F�N�g����{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����SecurityShortageAccountHistPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link SecurityShortageAccountHistRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static SecurityShortageAccountHistRow findRowByPk( SecurityShortageAccountHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SecurityShortageAccountHistRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,String)}�����{@@link #forRow(SecurityShortageAccountHistRow)}���g�p���Ă��������B 
   */
    public static SecurityShortageAccountHistDao findDaoByPk( long p_accountId, String p_procDate ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountHistPK pk = new SecurityShortageAccountHistPK( p_accountId, p_procDate );
        SecurityShortageAccountHistRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(SecurityShortageAccountHistPK)}�����{@@link #forRow(SecurityShortageAccountHistRow)}���g�p���Ă��������B 
   */
    public static SecurityShortageAccountHistDao findDaoByPk( SecurityShortageAccountHistPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        SecurityShortageAccountHistRow row = findRowByPk( pk );
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
   * p_accountId, p_procDate, and �ɂĎw��̒l�����ӂ�{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_procDate �����Ώۂł���p_procDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_procDate, and �̒l�ƈ�v����{@@link SecurityShortageAccountHistRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static SecurityShortageAccountHistRow findRowByAccountIdProcDate( long p_accountId, String p_procDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            SecurityShortageAccountHistRow.TYPE,
            "account_id=? and proc_date=?",
            null,
            new Object[] { new Long(p_accountId), p_procDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (SecurityShortageAccountHistRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query SecurityShortageAccountHistDao.findRowsByAccountIdProcDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByAccountIdProcDate(long, String)}�����{@@link #forRow(SecurityShortageAccountHistRow)}���g�p���Ă��������B 
   */
    public static SecurityShortageAccountHistDao findDaoByAccountIdProcDate( long p_accountId, String p_procDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByAccountIdProcDate( p_accountId, p_procDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
