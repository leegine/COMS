head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	HostRuitoOrderCancelDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.xbruito.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link HostRuitoOrderCancelDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostRuitoOrderCancelRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostRuitoOrderCancelPK 
 * @@see HostRuitoOrderCancelRow 
 */
public class HostRuitoOrderCancelDao extends DataAccessObject {


  /** 
   * ����{@@link HostRuitoOrderCancelDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostRuitoOrderCancelRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostRuitoOrderCancelRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostRuitoOrderCancelDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostRuitoOrderCancelDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostRuitoOrderCancelRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostRuitoOrderCancelRow )
                return new HostRuitoOrderCancelDao( (HostRuitoOrderCancelRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostRuitoOrderCancelRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostRuitoOrderCancelRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostRuitoOrderCancelRow}�I�u�W�F�N�g 
    */
    protected HostRuitoOrderCancelDao( HostRuitoOrderCancelRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostRuitoOrderCancelRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostRuitoOrderCancelRow getHostRuitoOrderCancelRow() {
        return row;
    }


  /** 
   * �w���{@@link HostRuitoOrderCancelRow}�I�u�W�F�N�g����{@@link HostRuitoOrderCancelDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostRuitoOrderCancelRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostRuitoOrderCancelDao}�擾�̂��߂Ɏw���{@@link HostRuitoOrderCancelRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostRuitoOrderCancelDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostRuitoOrderCancelDao forRow( HostRuitoOrderCancelRow row ) throws java.lang.IllegalArgumentException {
        return (HostRuitoOrderCancelDao) DataAccessObject.forRow( row );
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

        // (none) 

}
@