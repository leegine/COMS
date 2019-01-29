head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.20.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	CodeTranslationDao.java;


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
 * {@@link CodeTranslationDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link CodeTranslationRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see CodeTranslationPK 
 * @@see CodeTranslationRow 
 */
public class CodeTranslationDao extends DataAccessObject {


  /** 
   * ����{@@link CodeTranslationDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private CodeTranslationRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link CodeTranslationRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link CodeTranslationDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link CodeTranslationDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link CodeTranslationRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof CodeTranslationRow )
                return new CodeTranslationDao( (CodeTranslationRow) row );
            throw new java.lang.IllegalArgumentException( "Not a CodeTranslationRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link CodeTranslationRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link CodeTranslationRow}�I�u�W�F�N�g 
    */
    protected CodeTranslationDao( CodeTranslationRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link CodeTranslationRow}�I�u�W�F�N�g���擾���܂��B
   */
    public CodeTranslationRow getCodeTranslationRow() {
        return row;
    }


  /** 
   * �w���{@@link CodeTranslationRow}�I�u�W�F�N�g����{@@link CodeTranslationDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link CodeTranslationRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link CodeTranslationDao}�擾�̂��߂Ɏw���{@@link CodeTranslationRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link CodeTranslationDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static CodeTranslationDao forRow( CodeTranslationRow row ) throws java.lang.IllegalArgumentException {
        return (CodeTranslationDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link CodeTranslationRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link CodeTranslationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link CodeTranslationPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link CodeTranslationParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( CodeTranslationRow.TYPE );
    }


  /** 
   * {@@link CodeTranslationRow}����ӂɓ��肷��{@@link CodeTranslationPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link CodeTranslationRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link CodeTranslationParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link CodeTranslationPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static CodeTranslationPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link CodeTranslationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_codeType �����Ώۂł���p_codeType�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_code �����Ώۂł���p_code�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CodeTranslationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CodeTranslationRow findRowByPk( String p_codeType, String p_institutionCode, String p_code ) throws DataFindException, DataQueryException, DataNetworkException {
        CodeTranslationPK pk = new CodeTranslationPK( p_codeType, p_institutionCode, p_code );
        return findRowByPk( pk );
    }


  /** 
   * �w���CodeTranslationPK�I�u�W�F�N�g����{@@link CodeTranslationRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����CodeTranslationPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link CodeTranslationRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static CodeTranslationRow findRowByPk( CodeTranslationPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (CodeTranslationRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,String)}�����{@@link #forRow(CodeTranslationRow)}���g�p���Ă��������B 
   */
    public static CodeTranslationDao findDaoByPk( String p_codeType, String p_institutionCode, String p_code ) throws DataFindException, DataQueryException, DataNetworkException {
        CodeTranslationPK pk = new CodeTranslationPK( p_codeType, p_institutionCode, p_code );
        CodeTranslationRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(CodeTranslationPK)}�����{@@link #forRow(CodeTranslationRow)}���g�p���Ă��������B 
   */
    public static CodeTranslationDao findDaoByPk( CodeTranslationPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        CodeTranslationRow row = findRowByPk( pk );
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
   * p_codeType, p_institutionCode, p_code, and �ɂĎw��̒l�����ӂ�{@@link CodeTranslationRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_codeType �����Ώۂł���p_codeType�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_code �����Ώۂł���p_code�t�B�[���h�̒l
   * 
   * @@return �����w���p_codeType, p_institutionCode, p_code, and �̒l�ƈ�v����{@@link CodeTranslationRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static CodeTranslationRow findRowByCodeTypeInstitutionCodeCode( String p_codeType, String p_institutionCode, String p_code ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            CodeTranslationRow.TYPE,
            "code_type=? and institution_code=? and code=?",
            null,
            new Object[] { p_codeType, p_institutionCode, p_code } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (CodeTranslationRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query CodeTranslationDao.findRowsByCodeTypeInstitutionCodeCode(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByCodeTypeInstitutionCodeCode(String, String, String)}�����{@@link #forRow(CodeTranslationRow)}���g�p���Ă��������B 
   */
    public static CodeTranslationDao findDaoByCodeTypeInstitutionCodeCode( String p_codeType, String p_institutionCode, String p_code ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByCodeTypeInstitutionCodeCode( p_codeType, p_institutionCode, p_code ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
