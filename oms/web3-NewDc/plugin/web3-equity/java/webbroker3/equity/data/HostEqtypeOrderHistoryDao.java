head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostEqtypeOrderHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.equity.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostEqtypeOrderHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostEqtypeOrderHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostEqtypeOrderHistoryPK 
 * @@see HostEqtypeOrderHistoryRow 
 */
public class HostEqtypeOrderHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link HostEqtypeOrderHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostEqtypeOrderHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostEqtypeOrderHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostEqtypeOrderHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostEqtypeOrderHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostEqtypeOrderHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostEqtypeOrderHistoryRow )
                return new HostEqtypeOrderHistoryDao( (HostEqtypeOrderHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostEqtypeOrderHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostEqtypeOrderHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g 
    */
    protected HostEqtypeOrderHistoryDao( HostEqtypeOrderHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostEqtypeOrderHistoryRow getHostEqtypeOrderHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g����{@@link HostEqtypeOrderHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostEqtypeOrderHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostEqtypeOrderHistoryDao}�擾�̂��߂Ɏw���{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostEqtypeOrderHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostEqtypeOrderHistoryDao forRow( HostEqtypeOrderHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (HostEqtypeOrderHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostEqtypeOrderHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostEqtypeOrderHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostEqtypeOrderHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostEqtypeOrderHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostEqtypeOrderHistoryRow.TYPE );
    }


  /** 
   * {@@link HostEqtypeOrderHistoryRow}����ӂɓ��肷��{@@link HostEqtypeOrderHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostEqtypeOrderHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostEqtypeOrderHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostEqtypeOrderHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostEqtypeOrderHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostEqtypeOrderHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostEqtypeOrderHistoryRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderHistoryPK pk = new HostEqtypeOrderHistoryPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostEqtypeOrderHistoryPK�I�u�W�F�N�g����{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostEqtypeOrderHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostEqtypeOrderHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostEqtypeOrderHistoryRow findRowByPk( HostEqtypeOrderHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostEqtypeOrderHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostEqtypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static HostEqtypeOrderHistoryDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderHistoryPK pk = new HostEqtypeOrderHistoryPK( p_rowid );
        HostEqtypeOrderHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostEqtypeOrderHistoryPK)}�����{@@link #forRow(HostEqtypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static HostEqtypeOrderHistoryDao findDaoByPk( HostEqtypeOrderHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostEqtypeOrderHistoryRow row = findRowByPk( pk );
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

        // (none) 

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_status, p_institutionCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_institutionCode, and �̒l�ƈ�v����{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusInstitutionCode( String p_status, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderHistoryRow.TYPE,
            "status=? and institution_code=?",
            null,
            new Object[] { p_status, p_institutionCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusInstitutionCode(String, String)}�����{@@link #forRow(HostEqtypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusInstitutionCode( String p_status, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusInstitutionCode( p_status, p_institutionCode ) );
    }


  /** 
   * p_corpCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_corpCode �����Ώۂł���p_corpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_corpCode, and �̒l�ƈ�v����{@@link HostEqtypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostEqtypeOrderHistoryRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCorpCode(String)}�����{@@link #forRow(HostEqtypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }

}
@
