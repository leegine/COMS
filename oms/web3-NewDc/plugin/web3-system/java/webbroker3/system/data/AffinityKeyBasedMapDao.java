head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	AffinityKeyBasedMapDao.java;


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
 * {@@link AffinityKeyBasedMapDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AffinityKeyBasedMapRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AffinityKeyBasedMapPK 
 * @@see AffinityKeyBasedMapRow 
 */
public class AffinityKeyBasedMapDao extends DataAccessObject {


  /** 
   * ����{@@link AffinityKeyBasedMapDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AffinityKeyBasedMapRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AffinityKeyBasedMapRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AffinityKeyBasedMapDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AffinityKeyBasedMapDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AffinityKeyBasedMapRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AffinityKeyBasedMapRow )
                return new AffinityKeyBasedMapDao( (AffinityKeyBasedMapRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AffinityKeyBasedMapRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AffinityKeyBasedMapRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g 
    */
    protected AffinityKeyBasedMapDao( AffinityKeyBasedMapRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AffinityKeyBasedMapRow getAffinityKeyBasedMapRow() {
        return row;
    }


  /** 
   * �w���{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g����{@@link AffinityKeyBasedMapDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AffinityKeyBasedMapRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AffinityKeyBasedMapDao}�擾�̂��߂Ɏw���{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AffinityKeyBasedMapDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AffinityKeyBasedMapDao forRow( AffinityKeyBasedMapRow row ) throws java.lang.IllegalArgumentException {
        return (AffinityKeyBasedMapDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AffinityKeyBasedMapRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AffinityKeyBasedMapRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AffinityKeyBasedMapPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AffinityKeyBasedMapParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AffinityKeyBasedMapRow.TYPE );
    }


  /** 
   * {@@link AffinityKeyBasedMapRow}����ӂɓ��肷��{@@link AffinityKeyBasedMapPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AffinityKeyBasedMapRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AffinityKeyBasedMapParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AffinityKeyBasedMapPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AffinityKeyBasedMapPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_appId �����Ώۂł���p_appId�t�B�[���h�̒l
   * @@param p_dbId �����Ώۂł���p_dbId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AffinityKeyBasedMapRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AffinityKeyBasedMapRow findRowByPk( String p_appId, String p_dbId ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityKeyBasedMapPK pk = new AffinityKeyBasedMapPK( p_appId, p_dbId );
        return findRowByPk( pk );
    }


  /** 
   * �w���AffinityKeyBasedMapPK�I�u�W�F�N�g����{@@link AffinityKeyBasedMapRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AffinityKeyBasedMapPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AffinityKeyBasedMapRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AffinityKeyBasedMapRow findRowByPk( AffinityKeyBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AffinityKeyBasedMapRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(AffinityKeyBasedMapRow)}���g�p���Ă��������B 
   */
    public static AffinityKeyBasedMapDao findDaoByPk( String p_appId, String p_dbId ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityKeyBasedMapPK pk = new AffinityKeyBasedMapPK( p_appId, p_dbId );
        AffinityKeyBasedMapRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AffinityKeyBasedMapPK)}�����{@@link #forRow(AffinityKeyBasedMapRow)}���g�p���Ă��������B 
   */
    public static AffinityKeyBasedMapDao findDaoByPk( AffinityKeyBasedMapPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AffinityKeyBasedMapRow row = findRowByPk( pk );
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
