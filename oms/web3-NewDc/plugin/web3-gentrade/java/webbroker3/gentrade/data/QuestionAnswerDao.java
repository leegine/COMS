head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.15.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	QuestionAnswerDao.java;


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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link QuestionAnswerDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link QuestionAnswerRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see QuestionAnswerPK 
 * @@see QuestionAnswerRow 
 */
public class QuestionAnswerDao extends DataAccessObject {


  /** 
   * ����{@@link QuestionAnswerDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private QuestionAnswerRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link QuestionAnswerRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link QuestionAnswerDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link QuestionAnswerDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link QuestionAnswerRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof QuestionAnswerRow )
                return new QuestionAnswerDao( (QuestionAnswerRow) row );
            throw new java.lang.IllegalArgumentException( "Not a QuestionAnswerRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link QuestionAnswerRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link QuestionAnswerRow}�I�u�W�F�N�g 
    */
    protected QuestionAnswerDao( QuestionAnswerRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link QuestionAnswerRow}�I�u�W�F�N�g���擾���܂��B
   */
    public QuestionAnswerRow getQuestionAnswerRow() {
        return row;
    }


  /** 
   * �w���{@@link QuestionAnswerRow}�I�u�W�F�N�g����{@@link QuestionAnswerDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link QuestionAnswerRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link QuestionAnswerDao}�擾�̂��߂Ɏw���{@@link QuestionAnswerRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link QuestionAnswerDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static QuestionAnswerDao forRow( QuestionAnswerRow row ) throws java.lang.IllegalArgumentException {
        return (QuestionAnswerDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link QuestionAnswerRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link QuestionAnswerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link QuestionAnswerPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link QuestionAnswerParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( QuestionAnswerRow.TYPE );
    }


  /** 
   * {@@link QuestionAnswerRow}����ӂɓ��肷��{@@link QuestionAnswerPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link QuestionAnswerRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link QuestionAnswerParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link QuestionAnswerPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static QuestionAnswerPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link QuestionAnswerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_questionDiv �����Ώۂł���p_questionDiv�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * @@param p_questionNo �����Ώۂł���p_questionNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QuestionAnswerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QuestionAnswerRow findRowByPk( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionAnswerPK pk = new QuestionAnswerPK( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���QuestionAnswerPK�I�u�W�F�N�g����{@@link QuestionAnswerRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����QuestionAnswerPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link QuestionAnswerRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static QuestionAnswerRow findRowByPk( QuestionAnswerPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (QuestionAnswerRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String,String,String)}�����{@@link #forRow(QuestionAnswerRow)}���g�p���Ă��������B 
   */
    public static QuestionAnswerDao findDaoByPk( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionAnswerPK pk = new QuestionAnswerPK( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo );
        QuestionAnswerRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(QuestionAnswerPK)}�����{@@link #forRow(QuestionAnswerRow)}���g�p���Ă��������B 
   */
    public static QuestionAnswerDao findDaoByPk( QuestionAnswerPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        QuestionAnswerRow row = findRowByPk( pk );
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


  /** 
   * p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo, and �ɂĎw��̒l�����ӂ�{@@link QuestionAnswerRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_questionDiv �����Ώۂł���p_questionDiv�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * @@param p_questionNo �����Ώۂł���p_questionNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo, and �̒l�ƈ�v����{@@link QuestionAnswerRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static QuestionAnswerRow findRowByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            QuestionAnswerRow.TYPE,
            "institution_code=? and branch_code=? and question_div=? and order_request_number=? and question_no=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (QuestionAnswerRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query QuestionAnswerDao.findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo(String, String, String, String, String)}�����{@@link #forRow(QuestionAnswerRow)}���g�p���Ă��������B 
   */
    public static QuestionAnswerDao findDaoByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber, String p_questionNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeBranchCodeQuestionDivOrderRequestNumberQuestionNo( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, p_questionNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, and �ɂĎw��̒l�Ɉ�v����{@@link QuestionAnswerRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_branchCode �����Ώۂł���p_branchCode�t�B�[���h�̒l
   * @@param p_questionDiv �����Ώۂł���p_questionDiv�t�B�[���h�̒l
   * @@param p_orderRequestNumber �����Ώۂł���p_orderRequestNumber�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber, and �̒l�ƈ�v����{@@link QuestionAnswerRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            QuestionAnswerRow.TYPE,
            "institution_code=? and branch_code=? and question_div=? and order_request_number=?",
            null,
            new Object[] { p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber(String, String, String, String)}�����{@@link #forRow(QuestionAnswerRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber( String p_institutionCode, String p_branchCode, String p_questionDiv, String p_orderRequestNumber ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeBranchCodeQuestionDivOrderRequestNumber( p_institutionCode, p_branchCode, p_questionDiv, p_orderRequestNumber ) );
    }

}
@
