head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	RequisitionAccountMarginDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpoweradmin.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.tradingpoweradmin.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link RequisitionAccountMarginDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link RequisitionAccountMarginRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see RequisitionAccountMarginPK 
 * @@see RequisitionAccountMarginRow 
 */
public class RequisitionAccountMarginDao extends DataAccessObject {


  /** 
   * ����{@@link RequisitionAccountMarginDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private RequisitionAccountMarginRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link RequisitionAccountMarginRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link RequisitionAccountMarginDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link RequisitionAccountMarginDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link RequisitionAccountMarginRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof RequisitionAccountMarginRow )
                return new RequisitionAccountMarginDao( (RequisitionAccountMarginRow) row );
            throw new java.lang.IllegalArgumentException( "Not a RequisitionAccountMarginRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link RequisitionAccountMarginRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link RequisitionAccountMarginRow}�I�u�W�F�N�g 
    */
    protected RequisitionAccountMarginDao( RequisitionAccountMarginRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link RequisitionAccountMarginRow}�I�u�W�F�N�g���擾���܂��B
   */
    public RequisitionAccountMarginRow getRequisitionAccountMarginRow() {
        return row;
    }


  /** 
   * �w���{@@link RequisitionAccountMarginRow}�I�u�W�F�N�g����{@@link RequisitionAccountMarginDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link RequisitionAccountMarginRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link RequisitionAccountMarginDao}�擾�̂��߂Ɏw���{@@link RequisitionAccountMarginRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link RequisitionAccountMarginDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static RequisitionAccountMarginDao forRow( RequisitionAccountMarginRow row ) throws java.lang.IllegalArgumentException {
        return (RequisitionAccountMarginDao) DataAccessObject.forRow( row );
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
