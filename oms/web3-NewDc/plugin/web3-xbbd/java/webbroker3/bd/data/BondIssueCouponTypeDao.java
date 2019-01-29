head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	BondIssueCouponTypeDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.bd.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.*;

/** 
 * {@@link BondIssueCouponTypeDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link BondIssueCouponTypeRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see BondIssueCouponTypePK 
 * @@see BondIssueCouponTypeRow 
 */
public class BondIssueCouponTypeDao extends DataAccessObject {


  /** 
   * ����{@@link BondIssueCouponTypeDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private BondIssueCouponTypeRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link BondIssueCouponTypeRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link BondIssueCouponTypeDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link BondIssueCouponTypeDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link BondIssueCouponTypeRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof BondIssueCouponTypeRow )
                return new BondIssueCouponTypeDao( (BondIssueCouponTypeRow) row );
            throw new java.lang.IllegalArgumentException( "Not a BondIssueCouponTypeRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link BondIssueCouponTypeRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link BondIssueCouponTypeRow}�I�u�W�F�N�g 
    */
    protected BondIssueCouponTypeDao( BondIssueCouponTypeRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link BondIssueCouponTypeRow}�I�u�W�F�N�g���擾���܂��B
   */
    public BondIssueCouponTypeRow getBondIssueCouponTypeRow() {
        return row;
    }


  /** 
   * �w���{@@link BondIssueCouponTypeRow}�I�u�W�F�N�g����{@@link BondIssueCouponTypeDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link BondIssueCouponTypeRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link BondIssueCouponTypeDao}�擾�̂��߂Ɏw���{@@link BondIssueCouponTypeRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link BondIssueCouponTypeDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static BondIssueCouponTypeDao forRow( BondIssueCouponTypeRow row ) throws java.lang.IllegalArgumentException {
        return (BondIssueCouponTypeDao) DataAccessObject.forRow( row );
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
   * p_productId, and �ɂĎw��̒l�Ɉ�v����{@@link BondIssueCouponTypeRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_productId �����Ώۂł���p_productId�t�B�[���h�̒l
   * 
   * @@return �����w���p_productId, and �̒l�ƈ�v����{@@link BondIssueCouponTypeRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByProductId( long p_productId ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            BondIssueCouponTypeRow.TYPE,
            "product_id=?",
            null,
            new Object[] { new Long(p_productId) } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByProductId(long)}�����{@@link #forRow(BondIssueCouponTypeRow)}���g�p���Ă��������B 
   */
    public static List findDaosByProductId( long p_productId ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByProductId( p_productId ) );
    }

}
@
