head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.22.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityRangeBasedMapDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.system.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.system.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link AffinityRangeBasedMapDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AffinityRangeBasedMapRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AffinityRangeBasedMapPK 
 * @@see AffinityRangeBasedMapRow 
 */
public class AffinityRangeBasedMapDao extends DataAccessObject {


  /** 
   * ����{@@link AffinityRangeBasedMapDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AffinityRangeBasedMapRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AffinityRangeBasedMapRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AffinityRangeBasedMapDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AffinityRangeBasedMapDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AffinityRangeBasedMapRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AffinityRangeBasedMapRow )
                return new AffinityRangeBasedMapDao( (AffinityRangeBasedMapRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AffinityRangeBasedMapRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AffinityRangeBasedMapRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g 
    */
    protected AffinityRangeBasedMapDao( AffinityRangeBasedMapRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AffinityRangeBasedMapRow getAffinityRangeBasedMapRow() {
        return row;
    }


  /** 
   * �w���{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g����{@@link AffinityRangeBasedMapDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AffinityRangeBasedMapRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AffinityRangeBasedMapDao}�擾�̂��߂Ɏw���{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AffinityRangeBasedMapDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AffinityRangeBasedMapDao forRow( AffinityRangeBasedMapRow row ) throws java.lang.IllegalArgumentException {
        return (AffinityRangeBasedMapDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AffinityRangeBasedMapRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AffinityRangeBasedMapRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AffinityRangeBasedMapPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AffinityRangeBasedMapParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AffinityRangeBasedMapRow.TYPE );
    }


  /** 
   * {@@link AffinityRangeBasedMapRow}����ӂɓ��肷��{@@link AffinityRangeBasedMapPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AffinityRangeBasedMapRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AffinityRangeBasedMapParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AffinityRangeBasedMapPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AffinityRangeBasedMapPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_keyStart �����Ώۂł���p_keyStart�t�B�[���h�̒l
   * @@param p_keyEnd �����Ώۂł���p_keyEnd�t�B�[���h�̒l
   * @@param p_rangeOrderNo �����Ώۂł���p_rangeOrderNo�t�B�[���h�̒l
   * @@param p_serverType �����Ώۂł���p_serverType�t�B�[���h�̒l
   * @@param p_ctxName �����Ώۂł���p_ctxName�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AffinityRangeBasedMapRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AffinityRangeBasedMapRow findRowByPk( long p_keyStart, long p_keyEnd, int p_rangeOrderNo, int p_serverType, String p_ctxName ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityRangeBasedMapPK pk = new AffinityRangeBasedMapPK( p_keyStart, p_keyEnd, p_rangeOrderNo, p_serverType, p_ctxName );
        return findRowByPk( pk );
    }


  /** 
   * �w���AffinityRangeBasedMapPK�I�u�W�F�N�g����{@@link AffinityRangeBasedMapRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AffinityRangeBasedMapPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AffinityRangeBasedMapRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AffinityRangeBasedMapRow findRowByPk( AffinityRangeBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AffinityRangeBasedMapRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long,long,int,int,String)}�����{@@link #forRow(AffinityRangeBasedMapRow)}���g�p���Ă��������B 
   */
    public static AffinityRangeBasedMapDao findDaoByPk( long p_keyStart, long p_keyEnd, int p_rangeOrderNo, int p_serverType, String p_ctxName ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityRangeBasedMapPK pk = new AffinityRangeBasedMapPK( p_keyStart, p_keyEnd, p_rangeOrderNo, p_serverType, p_ctxName );
        AffinityRangeBasedMapRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AffinityRangeBasedMapPK)}�����{@@link #forRow(AffinityRangeBasedMapRow)}���g�p���Ă��������B 
   */
    public static AffinityRangeBasedMapDao findDaoByPk( AffinityRangeBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityRangeBasedMapRow row = findRowByPk( pk );
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
