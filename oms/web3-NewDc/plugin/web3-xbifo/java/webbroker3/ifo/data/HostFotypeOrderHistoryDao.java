head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostFotypeOrderHistoryDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.ifo.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;

/** 
 * {@@link HostFotypeOrderHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostFotypeOrderHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostFotypeOrderHistoryPK 
 * @@see HostFotypeOrderHistoryRow 
 */
public class HostFotypeOrderHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link HostFotypeOrderHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostFotypeOrderHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostFotypeOrderHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostFotypeOrderHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostFotypeOrderHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostFotypeOrderHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostFotypeOrderHistoryRow )
                return new HostFotypeOrderHistoryDao( (HostFotypeOrderHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostFotypeOrderHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostFotypeOrderHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g 
    */
    protected HostFotypeOrderHistoryDao( HostFotypeOrderHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostFotypeOrderHistoryRow getHostFotypeOrderHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g����{@@link HostFotypeOrderHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostFotypeOrderHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostFotypeOrderHistoryDao}�擾�̂��߂Ɏw���{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostFotypeOrderHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostFotypeOrderHistoryDao forRow( HostFotypeOrderHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (HostFotypeOrderHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostFotypeOrderHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostFotypeOrderHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostFotypeOrderHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostFotypeOrderHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostFotypeOrderHistoryRow.TYPE );
    }


  /** 
   * {@@link HostFotypeOrderHistoryRow}����ӂɓ��肷��{@@link HostFotypeOrderHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostFotypeOrderHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostFotypeOrderHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostFotypeOrderHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostFotypeOrderHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostFotypeOrderHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostFotypeOrderHistoryRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderHistoryPK pk = new HostFotypeOrderHistoryPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostFotypeOrderHistoryPK�I�u�W�F�N�g����{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostFotypeOrderHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostFotypeOrderHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostFotypeOrderHistoryRow findRowByPk( HostFotypeOrderHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostFotypeOrderHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostFotypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static HostFotypeOrderHistoryDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderHistoryPK pk = new HostFotypeOrderHistoryPK( p_rowid );
        HostFotypeOrderHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostFotypeOrderHistoryPK)}�����{@@link #forRow(HostFotypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static HostFotypeOrderHistoryDao findDaoByPk( HostFotypeOrderHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostFotypeOrderHistoryRow row = findRowByPk( pk );
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
   * p_status, p_institutionCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_status �����Ώۂł���p_status�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_status, p_institutionCode, and �̒l�ƈ�v����{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByStatusInstitutionCode( String p_status, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderHistoryRow.TYPE,
            "status=? and institution_code=?",
            null,
            new Object[] { p_status, p_institutionCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByStatusInstitutionCode(String, String)}�����{@@link #forRow(HostFotypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByStatusInstitutionCode( String p_status, String p_institutionCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByStatusInstitutionCode( p_status, p_institutionCode ) );
    }


  /** 
   * p_corpCode, and �ɂĎw��̒l�Ɉ�v����{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_corpCode �����Ώۂł���p_corpCode�t�B�[���h�̒l
   * 
   * @@return �����w���p_corpCode, and �̒l�ƈ�v����{@@link HostFotypeOrderHistoryRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostFotypeOrderHistoryRow.TYPE,
            "corp_code=?",
            null,
            new Object[] { p_corpCode } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByCorpCode(String)}�����{@@link #forRow(HostFotypeOrderHistoryRow)}���g�p���Ă��������B 
   */
    public static List findDaosByCorpCode( String p_corpCode ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByCorpCode( p_corpCode ) );
    }

}
@
