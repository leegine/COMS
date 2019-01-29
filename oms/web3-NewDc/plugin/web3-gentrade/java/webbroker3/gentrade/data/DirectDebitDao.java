head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.17.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	DirectDebitDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.gentrade.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link DirectDebitDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DirectDebitRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see DirectDebitPK 
 * @@see DirectDebitRow 
 */
public class DirectDebitDao extends DataAccessObject {


  /** 
   * ����{@@link DirectDebitDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DirectDebitRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DirectDebitRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DirectDebitDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DirectDebitDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DirectDebitRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DirectDebitRow )
                return new DirectDebitDao( (DirectDebitRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DirectDebitRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DirectDebitRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DirectDebitRow}�I�u�W�F�N�g 
    */
    protected DirectDebitDao( DirectDebitRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DirectDebitRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DirectDebitRow getDirectDebitRow() {
        return row;
    }


  /** 
   * �w���{@@link DirectDebitRow}�I�u�W�F�N�g����{@@link DirectDebitDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DirectDebitRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DirectDebitDao}�擾�̂��߂Ɏw���{@@link DirectDebitRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DirectDebitDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DirectDebitDao forRow( DirectDebitRow row ) throws java.lang.IllegalArgumentException {
        return (DirectDebitDao) DataAccessObject.forRow( row );
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


  /** 
   * p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv, and �ɂĎw��̒l�����ӂ�{@@link DirectDebitRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_accountCode �����Ώۂł���p_accountCode�t�B�[���h�̒l
   * @@param p_designateDiv �����Ώۂł���p_designateDiv�t�B�[���h�̒l
   * @@param p_comodity �����Ώۂł���p_comodity�t�B�[���h�̒l
   * @@param p_fundCode �����Ώۂł���p_fundCode�t�B�[���h�̒l
   * @@param p_transferDiv �����Ώۂł���p_transferDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv, and �̒l�ƈ�v����{@@link DirectDebitRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DirectDebitRow findRowByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv, String p_comodity, String p_fundCode, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DirectDebitRow.TYPE,
            "institution_code=? and branch_code=? and account_code=? and designate_div=? and comodity=? and fund_code=? and transfer_div=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DirectDebitRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DirectDebitDao.findRowsByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv(String, String, String, String, String, String, String)}�����{@@link #forRow(DirectDebitRow)}���g�p���Ă��������B 
   */
    public static DirectDebitDao findDaoByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv( String p_institutionCode, String p_branchCode, String p_accountCode, String p_designateDiv, String p_comodity, String p_fundCode, String p_transferDiv ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeAccountCodeDesignateDivComodityFundCodeTransferDiv( p_institutionCode, p_branchCode, p_accountCode, p_designateDiv, p_comodity, p_fundCode, p_transferDiv ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
