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
filename	AttentionInfoHistoryDao.java;


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
 * {@@link AttentionInfoHistoryDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AttentionInfoHistoryRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AttentionInfoHistoryPK 
 * @@see AttentionInfoHistoryRow 
 */
public class AttentionInfoHistoryDao extends DataAccessObject {


  /** 
   * ����{@@link AttentionInfoHistoryDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AttentionInfoHistoryRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AttentionInfoHistoryRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AttentionInfoHistoryDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AttentionInfoHistoryDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AttentionInfoHistoryRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AttentionInfoHistoryRow )
                return new AttentionInfoHistoryDao( (AttentionInfoHistoryRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AttentionInfoHistoryRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AttentionInfoHistoryRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AttentionInfoHistoryRow}�I�u�W�F�N�g 
    */
    protected AttentionInfoHistoryDao( AttentionInfoHistoryRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AttentionInfoHistoryRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AttentionInfoHistoryRow getAttentionInfoHistoryRow() {
        return row;
    }


  /** 
   * �w���{@@link AttentionInfoHistoryRow}�I�u�W�F�N�g����{@@link AttentionInfoHistoryDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AttentionInfoHistoryRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AttentionInfoHistoryDao}�擾�̂��߂Ɏw���{@@link AttentionInfoHistoryRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AttentionInfoHistoryDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AttentionInfoHistoryDao forRow( AttentionInfoHistoryRow row ) throws java.lang.IllegalArgumentException {
        return (AttentionInfoHistoryDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AttentionInfoHistoryRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AttentionInfoHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AttentionInfoHistoryPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AttentionInfoHistoryParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AttentionInfoHistoryRow.TYPE );
    }


  /** 
   * {@@link AttentionInfoHistoryRow}����ӂɓ��肷��{@@link AttentionInfoHistoryPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AttentionInfoHistoryRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AttentionInfoHistoryParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AttentionInfoHistoryPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AttentionInfoHistoryPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AttentionInfoHistoryPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AttentionInfoHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_attentionInfoHistoryId �����Ώۂł���p_attentionInfoHistoryId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AttentionInfoHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AttentionInfoHistoryRow findRowByPk( long p_attentionInfoHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        AttentionInfoHistoryPK pk = new AttentionInfoHistoryPK( p_attentionInfoHistoryId );
        return findRowByPk( pk );
    }


  /** 
   * �w���AttentionInfoHistoryPK�I�u�W�F�N�g����{@@link AttentionInfoHistoryRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AttentionInfoHistoryPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AttentionInfoHistoryRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AttentionInfoHistoryRow findRowByPk( AttentionInfoHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AttentionInfoHistoryRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(AttentionInfoHistoryRow)}���g�p���Ă��������B 
   */
    public static AttentionInfoHistoryDao findDaoByPk( long p_attentionInfoHistoryId ) throws DataFindException, DataQueryException, DataNetworkException {
        AttentionInfoHistoryPK pk = new AttentionInfoHistoryPK( p_attentionInfoHistoryId );
        AttentionInfoHistoryRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AttentionInfoHistoryPK)}�����{@@link #forRow(AttentionInfoHistoryRow)}���g�p���Ă��������B 
   */
    public static AttentionInfoHistoryDao findDaoByPk( AttentionInfoHistoryPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AttentionInfoHistoryRow row = findRowByPk( pk );
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
