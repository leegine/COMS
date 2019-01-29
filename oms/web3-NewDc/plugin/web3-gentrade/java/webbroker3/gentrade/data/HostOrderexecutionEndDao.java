head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.29.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	HostOrderexecutionEndDao.java;


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
 * {@@link HostOrderexecutionEndDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link HostOrderexecutionEndRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see HostOrderexecutionEndPK 
 * @@see HostOrderexecutionEndRow 
 */
public class HostOrderexecutionEndDao extends DataAccessObject {


  /** 
   * ����{@@link HostOrderexecutionEndDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private HostOrderexecutionEndRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link HostOrderexecutionEndRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link HostOrderexecutionEndDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link HostOrderexecutionEndDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link HostOrderexecutionEndRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof HostOrderexecutionEndRow )
                return new HostOrderexecutionEndDao( (HostOrderexecutionEndRow) row );
            throw new java.lang.IllegalArgumentException( "Not a HostOrderexecutionEndRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link HostOrderexecutionEndRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g 
    */
    protected HostOrderexecutionEndDao( HostOrderexecutionEndRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g���擾���܂��B
   */
    public HostOrderexecutionEndRow getHostOrderexecutionEndRow() {
        return row;
    }


  /** 
   * �w���{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g����{@@link HostOrderexecutionEndDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link HostOrderexecutionEndRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link HostOrderexecutionEndDao}�擾�̂��߂Ɏw���{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link HostOrderexecutionEndDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static HostOrderexecutionEndDao forRow( HostOrderexecutionEndRow row ) throws java.lang.IllegalArgumentException {
        return (HostOrderexecutionEndDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link HostOrderexecutionEndRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link HostOrderexecutionEndRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link HostOrderexecutionEndPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link HostOrderexecutionEndParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( HostOrderexecutionEndRow.TYPE );
    }


  /** 
   * {@@link HostOrderexecutionEndRow}����ӂɓ��肷��{@@link HostOrderexecutionEndPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link HostOrderexecutionEndRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link HostOrderexecutionEndParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link HostOrderexecutionEndPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static HostOrderexecutionEndPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with non-long value components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_rowid �����Ώۂł���p_rowid�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostOrderexecutionEndRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostOrderexecutionEndRow findRowByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOrderexecutionEndPK pk = new HostOrderexecutionEndPK( p_rowid );
        return findRowByPk( pk );
    }


  /** 
   * �w���HostOrderexecutionEndPK�I�u�W�F�N�g����{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����HostOrderexecutionEndPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link HostOrderexecutionEndRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static HostOrderexecutionEndRow findRowByPk( HostOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (HostOrderexecutionEndRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String)}�����{@@link #forRow(HostOrderexecutionEndRow)}���g�p���Ă��������B 
   */
    public static HostOrderexecutionEndDao findDaoByPk( String p_rowid ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOrderexecutionEndPK pk = new HostOrderexecutionEndPK( p_rowid );
        HostOrderexecutionEndRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(HostOrderexecutionEndPK)}�����{@@link #forRow(HostOrderexecutionEndRow)}���g�p���Ă��������B 
   */
    public static HostOrderexecutionEndDao findDaoByPk( HostOrderexecutionEndPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        HostOrderexecutionEndRow row = findRowByPk( pk );
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


  /** 
   * p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv, and �ɂĎw��̒l�Ɉ�v����{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_requestCode �����Ώۂł���p_requestCode�t�B�[���h�̒l
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_productType �����Ώۂł���p_productType�t�B�[���h�̒l
   * @@param p_futureOptionDiv �����Ώۂł���p_futureOptionDiv�t�B�[���h�̒l
   * 
   * @@return �����w���p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv, and �̒l�ƈ�v����{@@link HostOrderexecutionEndRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByRequestCodeInstitutionCodeProductTypeFutureOptionDiv( String p_requestCode, String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            HostOrderexecutionEndRow.TYPE,
            "request_code=? and institution_code=? and product_type=? and future_option_div=?",
            null,
            new Object[] { p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByRequestCodeInstitutionCodeProductTypeFutureOptionDiv(String, String, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, String)}�����{@@link #forRow(HostOrderexecutionEndRow)}���g�p���Ă��������B 
   */
    public static List findDaosByRequestCodeInstitutionCodeProductTypeFutureOptionDiv( String p_requestCode, String p_institutionCode, com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum p_productType, String p_futureOptionDiv ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByRequestCodeInstitutionCodeProductTypeFutureOptionDiv( p_requestCode, p_institutionCode, p_productType, p_futureOptionDiv ) );
    }

}
@
