head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostAttentionInfoNotifyDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.eqtypeadmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;

/** 
 * {@@link HostAttentionInfoNotifyDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostAttentionInfoNotifyRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostAttentionInfoNotifyPK 
 * @@see HostAttentionInfoNotifyRow 
 */
public class HostAttentionInfoNotifyDao extends DataAccessObject {


  /** 
   * ����{@@link HostAttentionInfoNotifyDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostAttentionInfoNotifyRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostAttentionInfoNotifyRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostAttentionInfoNotifyDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostAttentionInfoNotifyDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostAttentionInfoNotifyRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostAttentionInfoNotifyRow )
                return new HostAttentionInfoNotifyDao( (HostAttentionInfoNotifyRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostAttentionInfoNotifyRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostAttentionInfoNotifyRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostAttentionInfoNotifyRow}�I�u�W�F�N�g 
    */
    protected HostAttentionInfoNotifyDao( HostAttentionInfoNotifyRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostAttentionInfoNotifyRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostAttentionInfoNotifyRow getHostAttentionInfoNotifyRow() {
        return row;
    }


  /** 
   * �w���{@@link HostAttentionInfoNotifyRow}�I�u�W�F�N�g����{@@link HostAttentionInfoNotifyDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostAttentionInfoNotifyRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostAttentionInfoNotifyDao}�擾�̂��߂Ɏw���{@@link HostAttentionInfoNotifyRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostAttentionInfoNotifyDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostAttentionInfoNotifyDao forRow( HostAttentionInfoNotifyRow row ) throws java.lang.IllegalArgumentException {
        return (HostAttentionInfoNotifyDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostAttentionInfoNotifyRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostAttentionInfoNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostAttentionInfoNotifyPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostAttentionInfoNotifyParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostAttentionInfoNotifyRow.TYPE );
    }


  /** 
   * {@@link HostAttentionInfoNotifyRow}����ӂɓ��肷��{@@link HostAttentionInfoNotifyPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostAttentionInfoNotifyRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostAttentionInfoNotifyParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostAttentionInfoNotifyPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostAttentionInfoNotifyPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostAttentionInfoNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostAttentionInfoNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostAttentionInfoNotifyRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAttentionInfoNotifyPK pk = new HostAttentionInfoNotifyPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostAttentionInfoNotifyPK�I�u�W�F�N�g����{@@link HostAttentionInfoNotifyRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostAttentionInfoNotifyPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostAttentionInfoNotifyRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostAttentionInfoNotifyRow findRowByPk( HostAttentionInfoNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostAttentionInfoNotifyRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostAttentionInfoNotifyRow)}���g�p���Ă��������B 
   */
    public static HostAttentionInfoNotifyDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAttentionInfoNotifyPK pk = new HostAttentionInfoNotifyPK( p_rowid );
        HostAttentionInfoNotifyRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostAttentionInfoNotifyPK)}�����{@@link #forRow(HostAttentionInfoNotifyRow)}���g�p���Ă��������B 
   */
    public static HostAttentionInfoNotifyDao findDaoByPk( HostAttentionInfoNotifyPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostAttentionInfoNotifyRow row = findRowByPk( pk );
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

        // (none) 

}
@