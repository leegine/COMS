head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.30.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	TpOtherTempRestraintDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpower.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link TpOtherTempRestraintDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link TpOtherTempRestraintRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see TpOtherTempRestraintPK 
 * @@see TpOtherTempRestraintRow 
 */
public class TpOtherTempRestraintDao extends DataAccessObject {


  /** 
   * ����{@@link TpOtherTempRestraintDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private TpOtherTempRestraintRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link TpOtherTempRestraintRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link TpOtherTempRestraintDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link TpOtherTempRestraintDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link TpOtherTempRestraintRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof TpOtherTempRestraintRow )
                return new TpOtherTempRestraintDao( (TpOtherTempRestraintRow) row );
            throw new java.lang.IllegalArgumentException( "Not a TpOtherTempRestraintRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link TpOtherTempRestraintRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g 
    */
    protected TpOtherTempRestraintDao( TpOtherTempRestraintRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g���擾���܂��B
   */
    public TpOtherTempRestraintRow getTpOtherTempRestraintRow() {
        return row;
    }


  /** 
   * �w���{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g����{@@link TpOtherTempRestraintDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link TpOtherTempRestraintRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link TpOtherTempRestraintDao}�擾�̂��߂Ɏw���{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link TpOtherTempRestraintDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static TpOtherTempRestraintDao forRow( TpOtherTempRestraintRow row ) throws java.lang.IllegalArgumentException {
        return (TpOtherTempRestraintDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


      // (this object has no primary key components)


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


      // (this object has no primary key components)


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
   * p_accountId, p_deleteFlag, and �ɂĎw��̒l�Ɉ�v����{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_accountId �����Ώۂł���p_accountId�t�B�[���h�̒l
   * @@param p_deleteFlag �����Ώۂł���p_deleteFlag�t�B�[���h�̒l
   * 
   * @@return �����w���p_accountId, p_deleteFlag, and �̒l�ƈ�v����{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByAccountIdDeleteFlag( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpOtherTempRestraintRow.TYPE,
            "account_id=? and delete_flag=?",
            null,
            new Object[] { new Long(p_accountId), p_deleteFlag } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByAccountIdDeleteFlag(long, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum)}�����{@@link #forRow(TpOtherTempRestraintRow)}���g�p���Ă��������B 
   */
    public static List findDaosByAccountIdDeleteFlag( long p_accountId, com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum p_deleteFlag ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByAccountIdDeleteFlag( p_accountId, p_deleteFlag ) );
    }


  /** 
   * p_restraintDiv, p_deleteKey1, and �ɂĎw��̒l�Ɉ�v����{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_restraintDiv �����Ώۂł���p_restraintDiv�t�B�[���h�̒l
   * @@param p_deleteKey1 �����Ώۂł���p_deleteKey1�t�B�[���h�̒l
   * 
   * @@return �����w���p_restraintDiv, p_deleteKey1, and �̒l�ƈ�v����{@@link TpOtherTempRestraintRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRestraintDivDeleteKey1( String p_restraintDiv, String p_deleteKey1 ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            TpOtherTempRestraintRow.TYPE,
            "restraint_div=? and delete_key1=?",
            null,
            new Object[] { p_restraintDiv, p_deleteKey1 } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRestraintDivDeleteKey1(String, String)}�����{@@link #forRow(TpOtherTempRestraintRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRestraintDivDeleteKey1( String p_restraintDiv, String p_deleteKey1 ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRestraintDivDeleteKey1( p_restraintDiv, p_deleteKey1 ) );
    }

}
@
