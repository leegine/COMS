head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	AdmintoIfoOrderUnitDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.admintriggerorder.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link AdmintoIfoOrderUnitDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link AdmintoIfoOrderUnitRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see AdmintoIfoOrderUnitPK 
 * @@see AdmintoIfoOrderUnitRow 
 */
public class AdmintoIfoOrderUnitDao extends DataAccessObject {


  /** 
   * ����{@@link AdmintoIfoOrderUnitDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private AdmintoIfoOrderUnitRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link AdmintoIfoOrderUnitRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link AdmintoIfoOrderUnitDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link AdmintoIfoOrderUnitDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link AdmintoIfoOrderUnitRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof AdmintoIfoOrderUnitRow )
                return new AdmintoIfoOrderUnitDao( (AdmintoIfoOrderUnitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a AdmintoIfoOrderUnitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link AdmintoIfoOrderUnitRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link AdmintoIfoOrderUnitRow}�I�u�W�F�N�g 
    */
    protected AdmintoIfoOrderUnitDao( AdmintoIfoOrderUnitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link AdmintoIfoOrderUnitRow}�I�u�W�F�N�g���擾���܂��B
   */
    public AdmintoIfoOrderUnitRow getAdmintoIfoOrderUnitRow() {
        return row;
    }


  /** 
   * �w���{@@link AdmintoIfoOrderUnitRow}�I�u�W�F�N�g����{@@link AdmintoIfoOrderUnitDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link AdmintoIfoOrderUnitRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link AdmintoIfoOrderUnitDao}�擾�̂��߂Ɏw���{@@link AdmintoIfoOrderUnitRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link AdmintoIfoOrderUnitDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static AdmintoIfoOrderUnitDao forRow( AdmintoIfoOrderUnitRow row ) throws java.lang.IllegalArgumentException {
        return (AdmintoIfoOrderUnitDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link AdmintoIfoOrderUnitRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link AdmintoIfoOrderUnitRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link AdmintoIfoOrderUnitPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link AdmintoIfoOrderUnitParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( AdmintoIfoOrderUnitRow.TYPE );
    }


  /** 
   * {@@link AdmintoIfoOrderUnitRow}����ӂɓ��肷��{@@link AdmintoIfoOrderUnitPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link AdmintoIfoOrderUnitRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link AdmintoIfoOrderUnitParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link AdmintoIfoOrderUnitPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static AdmintoIfoOrderUnitPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new AdmintoIfoOrderUnitPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link AdmintoIfoOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_orderUnitId �����Ώۂł���p_orderUnitId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdmintoIfoOrderUnitRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdmintoIfoOrderUnitRow findRowByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoIfoOrderUnitPK pk = new AdmintoIfoOrderUnitPK( p_orderUnitId );
        return findRowByPk( pk );
    }


  /** 
   * �w���AdmintoIfoOrderUnitPK�I�u�W�F�N�g����{@@link AdmintoIfoOrderUnitRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����AdmintoIfoOrderUnitPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link AdmintoIfoOrderUnitRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static AdmintoIfoOrderUnitRow findRowByPk( AdmintoIfoOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AdmintoIfoOrderUnitRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(AdmintoIfoOrderUnitRow)}���g�p���Ă��������B 
   */
    public static AdmintoIfoOrderUnitDao findDaoByPk( long p_orderUnitId ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoIfoOrderUnitPK pk = new AdmintoIfoOrderUnitPK( p_orderUnitId );
        AdmintoIfoOrderUnitRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(AdmintoIfoOrderUnitPK)}�����{@@link #forRow(AdmintoIfoOrderUnitRow)}���g�p���Ă��������B 
   */
    public static AdmintoIfoOrderUnitDao findDaoByPk( AdmintoIfoOrderUnitPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        AdmintoIfoOrderUnitRow row = findRowByPk( pk );
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
