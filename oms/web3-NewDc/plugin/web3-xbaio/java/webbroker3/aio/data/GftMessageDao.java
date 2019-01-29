head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	GftMessageDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.aio.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import webbroker3.gentrade.data.*;

/** 
 * {@@link GftMessageDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link GftMessageRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see GftMessagePK 
 * @@see GftMessageRow 
 */
public class GftMessageDao extends DataAccessObject {


  /** 
   * ����{@@link GftMessageDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private GftMessageRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link GftMessageRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link GftMessageDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link GftMessageDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link GftMessageRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof GftMessageRow )
                return new GftMessageDao( (GftMessageRow) row );
            throw new java.lang.IllegalArgumentException( "Not a GftMessageRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link GftMessageRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link GftMessageRow}�I�u�W�F�N�g 
    */
    protected GftMessageDao( GftMessageRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link GftMessageRow}�I�u�W�F�N�g���擾���܂��B
   */
    public GftMessageRow getGftMessageRow() {
        return row;
    }


  /** 
   * �w���{@@link GftMessageRow}�I�u�W�F�N�g����{@@link GftMessageDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link GftMessageRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link GftMessageDao}�擾�̂��߂Ɏw���{@@link GftMessageRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link GftMessageDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static GftMessageDao forRow( GftMessageRow row ) throws java.lang.IllegalArgumentException {
        return (GftMessageDao) DataAccessObject.forRow( row );
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
   * p_messageDiv, p_cpy, p_brn, p_req, and �ɂĎw��̒l�Ɉ�v����{@@link GftMessageRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_messageDiv �����Ώۂł���p_messageDiv�t�B�[���h�̒l
   * @@param p_cpy �����Ώۂł���p_cpy�t�B�[���h�̒l
   * @@param p_brn �����Ώۂł���p_brn�t�B�[���h�̒l
   * @@param p_req �����Ώۂł���p_req�t�B�[���h�̒l
   * 
   * @@return �����w���p_messageDiv, p_cpy, p_brn, p_req, and �̒l�ƈ�v����{@@link GftMessageRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByMessageDivCpyBrnReq( String p_messageDiv, String p_cpy, String p_brn, String p_req ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            GftMessageRow.TYPE,
            "message_div=? and cpy=? and brn=? and req=?",
            null,
            new Object[] { p_messageDiv, p_cpy, p_brn, p_req } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByMessageDivCpyBrnReq(String, String, String, String)}�����{@@link #forRow(GftMessageRow)}���g�p���Ă��������B 
   */
    public static List findDaosByMessageDivCpyBrnReq( String p_messageDiv, String p_cpy, String p_brn, String p_req ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByMessageDivCpyBrnReq( p_messageDiv, p_cpy, p_brn, p_req ) );
    }

}
@
